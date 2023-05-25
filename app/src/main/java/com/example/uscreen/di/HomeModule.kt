package com.example.uscreen.di

import com.example.uscreen.features.dashboard.presentation.DashboardViewModel
import com.example.uscreen.features.example.presentation.ExampleViewModel
import com.example.uscreen.features.examplee.presentation.ExampleeViewModel
import com.example.uscreen.features.home.presentation.HomeViewModel
import com.example.uscreen.features.notifications.presentation.NotificationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Nikolay Yakushov on 18.05.2023.
 */
val homeModule = module {
    viewModel { DashboardViewModel() }
    viewModel { ExampleViewModel() }
    viewModel { HomeViewModel() }
    viewModel { NotificationsViewModel() }
    viewModel { ExampleeViewModel() }
}