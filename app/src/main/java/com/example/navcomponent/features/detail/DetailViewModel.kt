package com.example.navcomponent.features.detail

import com.example.navcomponent.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel : BaseViewModel() {

    private val _text = MutableStateFlow("This is detail Fragment")
    val text: StateFlow<String> = _text.asStateFlow()

}