package com.example.uscreen

import android.app.Application
import com.example.uscreen.features.home.di.homeModule
import org.koin.core.context.startKoin

/**
 * Created by Nikolay Yakushov on 18.05.2023.
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                homeModule,
            )
        }
    }
}