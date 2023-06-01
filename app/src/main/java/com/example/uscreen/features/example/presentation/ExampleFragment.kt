package com.example.uscreen.features.example.presentation

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.uscreen.R
import com.example.uscreen.base.BaseFragment
import com.example.uscreen.base.DefaultArgs
import com.example.uscreen.databinding.FragmentExampleBinding
import com.example.uscreen.utils.launchOnStarted
import com.example.uscreen.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ExampleFragment : BaseFragment<FragmentExampleBinding, ExampleViewModel, DefaultArgs>() {

    override val binding by viewBinding(FragmentExampleBinding::bind)
    override val viewModel by lazy { getViewModel<ExampleViewModel>() }
    override val layoutRes: Int = R.layout.fragment_example

    override fun initView(viewBinding: FragmentExampleBinding, context: Context, savedInstanceState: Bundle?) {}

    override fun onViewModelAttached(viewModel: ExampleViewModel) {
        viewModel.text.launchOnStarted(viewLifecycleOwner) {
            binding.textExample.text = it
        }
        binding.btn.setOnClickListener {
            navigateTo(R.id.next_action_examplee, DefaultArgs(false))
        }
    }
}