package com.example.navcomponent.features.dashboard

import com.example.navcomponent.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DashboardViewModel : BaseViewModel() {

    private val _text = MutableStateFlow("This is dashboard Fragment")
    val text: StateFlow<String> = _text.asStateFlow()

}