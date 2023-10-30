package com.example.navcomponent.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.navcomponent.utils.BaseViewModel

class DetailViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is detail Fragment"
    }

    val text: LiveData<String> = _text
}