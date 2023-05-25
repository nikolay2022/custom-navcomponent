package com.example.uscreen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
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
    private var flagBackPressed: Boolean = false

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
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            val currentFragmentId = destination.id

            if (tabs.contains(currentFragmentId)) {
                if (!flagBackPressed) {
                    menuStack.addBackStack(currentFragmentId)
                    backStacks.put(currentFragmentId, backStacks[currentFragmentId] ?: ArrayDeque())
                } else {
                    flagBackPressed = false
                }
                bottomNavigationView.menu.findItem(currentFragmentId).isChecked = true
            } else {
                if (!flagBackPressed) {
                    backStacks[menuStack.lastOrNull()]?.add(currentFragmentId)
                } else {
                    flagBackPressed = false
                }
                menuStack.lastOrNull()?.let {
                    bottomNavigationView.menu.findItem(it).isChecked = true
                }
            }
        }

        navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnItemSelectedListener { item ->

            menuStack.apply {
                removeIf { it == item.itemId }
                add(item.itemId)
            }

            when {
                !backStacks[item.itemId].isNullOrEmpty() -> {
                    backStacks[item.itemId]?.last()?.let { navController.navigate(it) }
                }

                else -> {
                    navController.navigate(item.itemId)
                }
            }

            true
        }
    }

    override fun onBackPressed() {
        flagBackPressed = true
        val currentFragmentId = navController.currentDestination?.id
        if (menuStack.isNotEmpty() && backStacks.isNotEmpty() && currentFragmentId != null) {
            if (backStacks[menuStack.lastOrNull()].isNullOrEmpty()) {
                menuStack.remove(menuStack.lastOrNull())
                if (menuStack.isEmpty()) {
                    finishAfterTransition()
                } else {
                    navigationWithBaskStack()
                }
            } else {
                backStacks[menuStack.lastOrNull()]?.remove(backStacks[menuStack.lastOrNull()]?.lastOrNull())
                navigationWithBaskStack()
            }
        } else {
            finishAfterTransition()
        }
    }

    private fun navigationWithBaskStack(){
        if (backStacks[menuStack.lastOrNull()].isNullOrEmpty()) {
            navController.navigate(menuStack.last())
        } else {
            backStacks[menuStack.lastOrNull()]?.last()
                ?.let { navController.navigate(it) }
        }
    }

    fun hideBottomNavigation() {
        bottomNavigationView.visibility = View.GONE
    }

    fun showBottomNavigation() {
        bottomNavigationView.visibility = View.VISIBLE
    }
}

fun ArrayDeque<Int>.addBackStack(item: Int) {
    this.apply {
        removeIf { it == item }
        add(item)
    }
}