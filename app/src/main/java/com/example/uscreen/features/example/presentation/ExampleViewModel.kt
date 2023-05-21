package com.example.uscreen.features.example.presentation

import com.example.uscreen.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExampleViewModel : BaseViewModel() {

    private val _text = MutableStateFlow("This is example Fragment")
    val text: StateFlow<String> = _text.asStateFlow()

}