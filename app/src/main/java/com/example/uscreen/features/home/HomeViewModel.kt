package com.example.uscreen.features.home

import com.example.uscreen.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : BaseViewModel() {

    private val _text = MutableStateFlow("This is home Fragment")
    val text: StateFlow<String> = _text.asStateFlow()

}