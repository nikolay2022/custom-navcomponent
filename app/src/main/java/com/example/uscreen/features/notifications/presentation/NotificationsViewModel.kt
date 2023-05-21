package com.example.uscreen.features.notifications.presentation

import com.example.uscreen.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationsViewModel : BaseViewModel() {

    private val _text = MutableStateFlow("This is notifications Fragment")
    val text: StateFlow<String> = _text.asStateFlow()

}