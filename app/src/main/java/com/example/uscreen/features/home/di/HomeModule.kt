package com.example.uscreen.features.home.di

import com.example.uscreen.features.home.presentation.dashboard.DashboardViewModel
import com.example.uscreen.features.home.presentation.example.ExampleViewModel
import com.example.uscreen.features.home.presentation.home.HomeViewModel
import com.example.uscreen.features.home.presentation.notifications.NotificationsViewModel
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
}