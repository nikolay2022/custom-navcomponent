package com.example.uscreen.utils

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.uscreen.MainActivity

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {

    protected abstract val binding: VB
    protected abstract val viewModel: VM

    private var args: DefaultArgs = DefaultArgs()

    override fun onResume() {
        super.onResume()
        if (args.hideBottomNavigation) {
            (requireActivity() as? MainActivity)?.hideBottomNavigation()
        } else {
            (requireActivity() as? MainActivity)?.showBottomNavigation()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        args = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(KEY, DefaultArgs::class.java) ?: DefaultArgs()
        } else {
            arguments?.getSerializable(KEY) as DefaultArgs
        }
        super.onCreate(savedInstanceState)
    }

    fun navigateTo(directions: Int, args: DefaultArgs = DefaultArgs()) {
        val bundle = Bundle()
        bundle.putSerializable(KEY, args)
        findNavController().navigate(directions, bundle)
    }

    private companion object {
        const val KEY = "key"
    }
}
