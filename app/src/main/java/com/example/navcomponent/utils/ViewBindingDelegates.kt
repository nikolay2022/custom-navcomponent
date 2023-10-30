package com.example.navcomponent.utils

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(crossinline factory: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        factory(layoutInflater)
    }

fun <T : ViewBinding> Fragment.viewBinding(binder: (View) -> T): FragmentViewBindingDelegate<T> {
    return FragmentViewBindingDelegate(this, binder)
}

@Suppress("unused")
class FragmentViewBindingDelegate<T : ViewBinding>(
    private val fragment: Fragment,
    private val binder: (View) -> T
) : ReadOnlyProperty<Fragment, T> {

    private var binding: T? = null

    private var isLifecycleObserved = false

    private val viewLifecycleOwnerLiveDataObserver = Observer<LifecycleOwner?> {
        val viewLifecycleOwner = it ?: return@Observer
        viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                binding = null
            }
        })
    }

    private val fragmentLifecycleOwnerLiveDataObserver = object : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            fragment.viewLifecycleOwnerLiveData
                .observeForever(viewLifecycleOwnerLiveDataObserver)
        }

        override fun onDestroy(owner: LifecycleOwner) {
            isLifecycleObserved = false
            fragment.viewLifecycleOwnerLiveData
                .removeObserver(viewLifecycleOwnerLiveDataObserver)
        }
    }

    init {
        addFragmentLifecycleOwnerObserver()
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        if (!isLifecycleObserved) {
            addFragmentLifecycleOwnerObserver()
        }

        val binding = this.binding
        if (binding != null) {
            return binding
        }

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException(
                "Should not attempt to get bindings when Fragment views are destroyed."
            )
        }

        return binder(thisRef.requireView())
            .also {
                this.binding = it
            }
    }

    private fun addFragmentLifecycleOwnerObserver() {
        isLifecycleObserved = true
        fragment.lifecycle.addObserver(fragmentLifecycleOwnerLiveDataObserver)
    }
}