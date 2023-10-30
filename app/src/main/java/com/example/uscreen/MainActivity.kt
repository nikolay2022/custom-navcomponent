package com.example.uscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.uscreen.databinding.ActivityMainBinding
import com.example.uscreen.utils.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

var menuStack: ArrayDeque<Int> = ArrayDeque()
val backStacks: MutableMap<Int, ArrayDeque<Int>> = mutableMapOf()

class MainActivity : AppCompatActivity() {

    private val bottomNavigationView: BottomNavigationView by lazy {
        binding.navView
    }
    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private var isBackPressed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        val tabs = setOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications,
        )

        appBarConfiguration = AppBarConfiguration(tabs)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            val nextFragmentId = destination.id

            if (tabs.contains(nextFragmentId)) {
                if (!isBackPressed) {
                    menuStack.addBackStack(nextFragmentId)
                    if (backStacks[nextFragmentId] == null) {
                        backStacks[nextFragmentId] = ArrayDeque()
                    }
                } else {
                    isBackPressed = false
                }
                bottomNavigationView.menu.findItem(nextFragmentId).isChecked = true
            } else {
                if (!isBackPressed) {
                    val menuLast = menuStack.lastOrNull()
                    val backStackLast = backStacks.getOrDefault(menuLast, null)

                    if (backStackLast?.lastOrNull() != nextFragmentId) {
                        backStackLast?.add(nextFragmentId)
                    }
                } else {
                    isBackPressed = false
                }
                menuStack.lastOrNull()?.let {
                    bottomNavigationView.menu.findItem(it).isChecked = true
                }
            }
        }

        navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnItemSelectedListener { item ->

            val builder = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setRestoreState(menuStack.contains(item.itemId))
                .setPopUpTo(
                    navController.graph.findStartDestination().id,
                    inclusive = false,
                    saveState = true
                )
            menuStack.addBackStack(item.itemId)

            when {
                !backStacks[item.itemId].isNullOrEmpty() -> {
                    backStacks[item.itemId]?.last()
                        ?.let { navController.navigate(it, null, builder.build()) }
                }

                else -> {
                    navController.navigate(item.itemId, null, builder.build())
                }
            }

            true
        }
    }

    override fun onBackPressed() {
        isBackPressed = true
        val currentFragmentId = navController.currentDestination?.id

        if (menuStack.isEmpty() || backStacks.isEmpty() || currentFragmentId == null) {
            finishAfterTransition()
            return
        }

        val menuStackLast = menuStack.last()
        val backStackLastArray = backStacks.getOrDefault(menuStackLast, null)

        if (backStackLastArray.isNullOrEmpty()) {
                menuStack.remove(menuStackLast)
            if (menuStack.isEmpty()) {
                finishAfterTransition()
            } else {
                navigationWithBaskStack()
            }
        } else {
            backStackLastArray.remove(backStackLastArray.last())
            navigationWithBaskStack()
        }
    }

    private fun navigationWithBaskStack() {
        val menuStackLast = menuStack.lastOrNull()
        val backStackLastArray = backStacks.getOrDefault(menuStackLast, null)

        val builder = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setRestoreState(!(backStackLastArray.isNullOrEmpty() && menuStack.last() == R.id.navigation_home))
            .setPopUpTo(
                navController.graph.findStartDestination().id,
                inclusive = false,
                saveState = true
            )

        navController.navigate(
            if (backStackLastArray.isNullOrEmpty()) {
                menuStack.last()
            } else {
                backStackLastArray.last()
            },
            null, builder.build()
        )
    }

    fun hideBottomNavigation() {
        bottomNavigationView.isVisible = false
    }

    fun showBottomNavigation() {
        bottomNavigationView.isVisible = true
    }
}

fun ArrayDeque<Int>.addBackStack(item: Int) {
    this.apply {
        removeIf { it == item }
        add(item)
    }
}