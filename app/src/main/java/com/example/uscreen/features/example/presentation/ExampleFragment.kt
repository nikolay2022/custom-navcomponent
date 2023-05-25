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
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ExampleFragment : BaseFragment<FragmentExampleBinding, ExampleViewModel, DefaultArgs>() {

    override val binding by lazy { FragmentExampleBinding.inflate(layoutInflater) }
    override val viewModel by lazy { getViewModel<ExampleViewModel>() }

    override fun initView(viewBinding: FragmentExampleBinding, context: Context, savedInstanceState: Bundle?) {}

    override fun onViewModelAttached(viewModel: ExampleViewModel) {
        viewModel.text.launchOnStarted(viewLifecycleOwner) {
            binding.textExample.text = it
        }
    }
}