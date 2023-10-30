package com.example.navcomponent.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.navcomponent.utils.BaseViewModel

class HomeViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}