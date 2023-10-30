package com.example.uscreen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.uscreen.utils.BaseViewModel

class HomeViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}