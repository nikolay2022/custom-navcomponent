package com.example.uscreen.features.dashboard.presentation

import com.example.uscreen.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel : BaseViewModel() {

    private val _text = MutableStateFlow("This is dashboard Fragment")
    val text: StateFlow<String> = _text.asStateFlow()

}