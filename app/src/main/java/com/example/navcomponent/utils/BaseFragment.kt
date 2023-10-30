package com.example.navcomponent.utils

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.navcomponent.MainActivity
import java.io.Serializable


open class DefaultArgs(
    val hideBottomNavigation: Boolean = false
): Serializable

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel, Args : DefaultArgs> : Fragment() {

    protected abstract val binding: VB
    protected abstract val viewModel: VM

    protected var args: Args = DefaultArgs() as Args

    @Suppress("UNCHECKED_CAST")
    fun <F : BaseFragment<VB, VM, Args>> withArgs(newArgs: Args): F {
        this.args = newArgs
        return this as F
    }

    override fun onResume() {
        super.onResume()
        if (args.hideBottomNavigation) {
            (requireActivity() as? MainActivity)?.hideBottomNavigation()
        } else {
            (requireActivity() as? MainActivity)?.showBottomNavigation()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (args.hideBottomNavigation) {
            (activity as? MainActivity)?.hideBottomNavigation()
        }
    }

    override fun onDestroyView() {
        if (args.hideBottomNavigation) {
            (activity as? MainActivity)?.showBottomNavigation()
        }
        super.onDestroyView()
    }

    fun navigateTo(directions: Int) {
        findNavController().navigate(directions)
    }
}