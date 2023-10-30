package com.example.navcomponent.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

inline fun <T> Flow<T>.launchOnCreated(lifecycleOwner: LifecycleOwner, crossinline action: suspend (T) -> Unit) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
            collect { action.invoke(it) }
        }
    }
}

inline fun <T> Flow<T>.launchOnStarted(lifecycleOwner: LifecycleOwner, crossinline action: suspend (T) -> Unit) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            collect { action.invoke(it) }
        }
    }
}

inline fun <T> Flow<T>.launchOnResumed(lifecycleOwner: LifecycleOwner, crossinline action: suspend (T) -> Unit) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            collect { action.invoke(it) }
        }
    }
}