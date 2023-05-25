package com.example.uscreen.features.examplee.presentation

import androidx.lifecycle.viewModelScope
import com.example.uscreen.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExampleeViewModel : BaseViewModel() {

    private val _text = MutableStateFlow("This is examplee Fragment")
    val text: StateFlow<String> = _text.asStateFlow()

    fun saveButton(text: String) {
        viewModelScope.launch {
            _text.emit(text)
        }
    }

}