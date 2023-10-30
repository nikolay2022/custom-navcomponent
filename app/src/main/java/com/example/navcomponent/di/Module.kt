package com.example.navcomponent.di

import com.example.navcomponent.features.dashboard.DashboardViewModel
import com.example.navcomponent.features.detail.DetailViewModel
import com.example.navcomponent.features.home.HomeViewModel
import com.example.navcomponent.features.notifications.NotificationsViewModel
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