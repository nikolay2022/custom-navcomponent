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
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private var menuStack: ArrayDeque<Int> = ArrayDeque()
    private val backStacks: MutableMap<Int, ArrayDeque<Int>> = mutableMapOf()
    private var isBackPressed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        val tabs = setOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications,
        )

        appBarConfiguration = AppBarConfiguration(tabs)
        bottomNavigationView = findViewById(R.id.nav_view)

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

            menuStack.addBackStack(item.itemId)
            val builder = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setRestoreState(true)
                .setPopUpTo(
                    navController.graph.findStartDestination().id,
                    inclusive = false,
                    saveState = true
                )

            when {
                !backStacks[item.itemId].isNullOrEmpty() -> {
                    backStacks[item.itemId]?.last()?.let { navController.navigate(it,null,builder.build()) }
                }

                else -> {
                    navController.navigate(item.itemId,null,builder.build())
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

                val field = NavController::class.java.getDeclaredField("backQueue")
                field.isAccessible = true
                val spisok = field.get(navController) as ArrayDeque<NavBackStackEntry>
                spisok.removeIf{it.destination.id == currentFragmentId}
                field.set(navController, spisok)

            }
        } else {
            backStackLastArray.remove(backStackLastArray.last())
            navigationWithBaskStack()

            val field = NavController::class.java.getDeclaredField("backQueue")
            field.isAccessible = true
            val spisok = field.get(navController) as ArrayDeque<NavBackStackEntry>
            spisok.removeIf{it.destination.id == currentFragmentId}
            field.set(navController, spisok)
        }
    }

    private fun navigationWithBaskStack() {
        val menuStackLast = menuStack.lastOrNull()
        val backStackLastArray = backStacks.getOrDefault(menuStackLast, null)

        val builder = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setRestoreState(true)
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