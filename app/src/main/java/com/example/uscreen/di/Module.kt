package com.example.uscreen.di

import com.example.uscreen.features.dashboard.DashboardViewModel
import com.example.uscreen.features.detail.DetailViewModel
import com.example.uscreen.features.home.HomeViewModel
import com.example.uscreen.features.notifications.NotificationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Nikolay Yakushov on 18.05.2023.
 */
val module = module {
    viewModel { DashboardViewModel() }
    viewModel { DetailViewModel() }
    viewModel { HomeViewModel() }
    viewModel { NotificationsViewModel() }
}