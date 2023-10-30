package com.example.navcomponent.features.home

import com.example.navcomponent.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : BaseViewModel() {

    private val _text = MutableStateFlow("This is home Fragment")
    val text: StateFlow<String> = _text.asStateFlow()

}