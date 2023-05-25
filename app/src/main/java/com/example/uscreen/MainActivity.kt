package com.example.uscreen

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.uscreen.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private var menuStack: ArrayDeque<Int> = ArrayDeque()
    private var isBackPressed: Boolean = false

    private val tabs = setOf(
        R.id.navigation_home,
        R.id.navigation_dashboard,
        R.id.navigation_notifications,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment

        appBarConfiguration = AppBarConfiguration(tabs)
        bottomNavigationView = findViewById(R.id.nav_view)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            val nextFragmentId = destination.id

            if (tabs.contains(nextFragmentId)) {
                if (!isBackPressed) {
                    menuStack.addBackStack(nextFragmentId)
                } else {
                    isBackPressed = false
                }
                menuStack.lastOrNull()
                    ?.let { bottomNavigationView.menu.findItem(it).isChecked = true }
            }

        }

        navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnItemSelectedListener {
            bottomNavigationView.menu.findItem(it.itemId).isChecked = true

            val navOptions = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setRestoreState(true)


//            if (it.order and Menu.CATEGORY_SECONDARY == 0) {
                navOptions.setPopUpTo(
                    navController.graph.findStartDestination().id,
                    inclusive = false,
                    saveState = true
                )
//            }

            val isSuccess = NavigationUI.onNavDestinationSelected(it, navController)

//            if (isSuccess == false && it.itemId == tabs.first()) {
            navController.navigate(it.itemId, null,  navOptions.build())
//            }
            true
        }

        onBackPressedDispatcher.addCallback(
            this /* lifecycle owner */,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    isBackPressed = true
                    val previousId =
                        navController.currentBackStack.value.getOrNull(navController.currentBackStack.value.lastIndex - 1)?.destination?.id

                    if (((navController.currentBackStack.value.count() <= 2 && navController.currentBackStack.value.last().destination.id == tabs.first()) ||
                                (navController.currentBackStack.value.count() >= 3 && previousId == tabs.first())) &&
                        menuStack.getOrNull(menuStack.lastIndex - 1) != null
                    ) {
                        val navOptions = NavOptions.Builder()
                            .setLaunchSingleTop(true)
                            .setRestoreState(true)
                            .setPopUpTo(
                                navController.graph.findStartDestination().id,
                                inclusive = false
                            )
                            .build()

                        menuStack.remove(menuStack.last())

                        navController.navigate(menuStack.last(), null, navOptions)
                    } else if (menuStack.count() == 1) {
                        Toast.makeText(
                            this@MainActivity,
                            "нажмите еще раз чтобы закрыть приложение",
                            Toast.LENGTH_SHORT
                        ).show()
                        menuStack.remove(menuStack.last())
                    } else {
                        finish()
                    }
                }
            })

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