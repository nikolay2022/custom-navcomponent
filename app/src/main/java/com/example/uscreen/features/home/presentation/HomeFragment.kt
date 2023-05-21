package com.example.uscreen.features.home.presentation

import android.content.Context
import android.os.Bundle
import com.example.uscreen.base.BaseFragment
import com.example.uscreen.base.DefaultArgs
import com.example.uscreen.databinding.FragmentHomeBinding
import com.example.uscreen.utils.launchOnStarted
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel, DefaultArgs>() {

    override val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    override val viewModel by lazy { getViewModel<HomeViewModel>() }

    override fun initView(viewBinding: FragmentHomeBinding, context: Context, savedInstanceState: Bundle?) {}

    override fun onViewModelAttached(viewModel: HomeViewModel) {
        viewModel.text.launchOnStarted(viewLifecycleOwner) {
            binding.textHome.text = it
        }
    }
}