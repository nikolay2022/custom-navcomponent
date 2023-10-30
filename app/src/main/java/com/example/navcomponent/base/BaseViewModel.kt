package com.example.navcomponent.base

import androidx.lifecycle.ViewModel

/**
 * Created by Nikolay Yakushov on 19.05.2023.
 */
abstract class BaseViewModel : ViewModel() {

    open fun onAttached() {}

    open fun onDetached() {}
}