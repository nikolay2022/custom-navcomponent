package com.example.uscreen.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewGroupViewBindingDelegate3<T : ViewBinding>(
    val viewGroup: ViewGroup,
    viewBindingFactory: (LayoutInflater, ViewGroup, Boolean) -> T
) : ReadOnlyProperty<ViewGroup, ViewBinding> {

    private var binding = viewBindingFactory(
        LayoutInflater.from(viewGroup.context),
        viewGroup,
        true
    )

    override fun getValue(thisRef: ViewGroup, property: KProperty<*>): ViewBinding {
        return binding
    }
}

class ViewGroupViewBindingDelegate2<T : ViewBinding>(
    val viewGroup: ViewGroup,
    viewBindingFactory: (LayoutInflater, ViewGroup) -> T
) : ReadOnlyProperty<ViewGroup, ViewBinding> {

    private var binding = viewBindingFactory(LayoutInflater.from(viewGroup.context), viewGroup)

    override fun getValue(thisRef: ViewGroup, property: KProperty<*>): ViewBinding {
        return binding
    }
}

fun <T : ViewBinding> ViewGroup.viewBindingViewGroup2(viewBindingFactory: (LayoutInflater, ViewGroup) -> T) =
    ViewGroupViewBindingDelegate2(this, viewBindingFactory)

fun <T : ViewBinding> ViewGroup.viewBindingViewGroup3(viewBindingFactory: (LayoutInflater, ViewGroup, Boolean) -> T) =
    ViewGroupViewBindingDelegate3(this, viewBindingFactory)