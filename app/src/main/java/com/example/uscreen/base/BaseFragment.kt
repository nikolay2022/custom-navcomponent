package com.example.uscreen.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.uscreen.MainActivity
import com.example.uscreen.backStacks

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel, Args : DefaultArgs> : Fragment() {

    protected abstract val binding: VB
    protected abstract val viewModel: VM

    protected var args: Args = DefaultArgs() as Args
    private var hasViewModelAttached = false

    private val viewLifecycleOwnerLiveDataObserver =
        Observer<LifecycleOwner?> { viewLifecycleOwner ->
            viewLifecycleOwner?.lifecycle?.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    if (hasViewModelAttached) {
                        detachViewModel(viewModel)
                    }
                }
            })
        }

    protected abstract fun onViewModelAttached(viewModel: VM)

    protected abstract fun initView(viewBinding: VB, context: Context, savedInstanceState: Bundle?)

    protected open fun onViewModelDetached(viewModel: VM) {}

    override fun onResume() {
        super.onResume()
        if (args.hideBottomNavigation) {
            (requireActivity() as? MainActivity)?.hideBottomNavigation()
        } else {
            (requireActivity() as? MainActivity)?.showBottomNavigation()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(KEY_ARGS, DefaultArgs::class.java)
        } else {
            arguments?.getSerializable(KEY_ARGS) as? DefaultArgs
        }?.let {
            args = it as Args
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        context?.let { initView(binding, it, savedInstanceState) }
        super.onViewCreated(view, savedInstanceState)
        if (hasViewModelAttached.not()) {
            attachViewModel(viewModel)
        }
    }

    protected fun navigateTo(directions: Int, args: DefaultArgs = DefaultArgs()) {
        val bundle = Bundle().apply { putSerializable(KEY_ARGS, args) }
        val builder = NavOptions.Builder()
            .setLaunchSingleTop(true)

        var restoreState = false
        backStacks.forEach { t, u ->
            if (t == directions || u.contains(directions)) {
                restoreState = true
            }
        }

        builder.setRestoreState(restoreState)
            .setPopUpTo(
                findNavController().graph.findStartDestination().id,
                inclusive = false,
                saveState = true
            )
        findNavController().navigate(directions, bundle, builder.build())
    }

    private fun attachViewModel(viewModel: VM) {
        hasViewModelAttached = true
        onViewModelAttached(viewModel)
        viewModel.onAttached()
    }

    private fun detachViewModel(viewModel: VM) {
        hasViewModelAttached = false
        onViewModelDetached(viewModel)
        viewModel.onDetached()
    }

    private companion object {
        const val KEY_ARGS = "key_args"
    }
}
