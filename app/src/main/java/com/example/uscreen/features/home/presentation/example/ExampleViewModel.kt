package com.example.uscreen.features.home.presentation.example

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.uscreen.utils.BaseViewModel

class ExampleViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is example Fragment"
    }

    val text: LiveData<String> = _text
}