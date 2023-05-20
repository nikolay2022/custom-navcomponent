package com.example.uscreen.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.uscreen.MainActivity

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {

    protected abstract val binding: VB
    protected abstract val viewModel: VM

    private var hideBottomNavigation: Boolean = false

    override fun onResume() {
        super.onResume()
        if (hideBottomNavigation) {
            (requireActivity() as? MainActivity)?.hideBottomNavigation()
        } else {
            (requireActivity() as? MainActivity)?.showBottomNavigation()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.let {
            hideBottomNavigation = it.getBoolean(HIDE_BOTTOM_NAVIGATION, false)
        }
        super.onCreate(savedInstanceState)
    }

    fun navigateTo(directions: Int, hideBottomNavigation: Boolean = false) {
        val bundle = Bundle()
        bundle.putBoolean(HIDE_BOTTOM_NAVIGATION, hideBottomNavigation)
        findNavController().navigate(directions, bundle)
    }

    private companion object {
        const val HIDE_BOTTOM_NAVIGATION = "hideBottomNavigation"
    }
}
