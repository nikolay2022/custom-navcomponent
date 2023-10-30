package com.example.uscreen.features.home

import android.content.Context
import android.os.Bundle
import com.example.uscreen.R
import com.example.uscreen.base.BaseFragment
import com.example.uscreen.base.DefaultArgs
import com.example.uscreen.databinding.FragmentHomeBinding
import com.example.uscreen.utils.launchOnStarted
import com.example.uscreen.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel, DefaultArgs>() {

    override val binding by viewBinding(FragmentHomeBinding::bind)
    override val viewModel by lazy { getViewModel<HomeViewModel>() }
    override val layoutRes: Int = R.layout.fragment_home

    override fun initView(viewBinding: FragmentHomeBinding, context: Context, savedInstanceState: Bundle?) {}

    override fun onViewModelAttached(viewModel: HomeViewModel) {
        viewModel.text.launchOnStarted(viewLifecycleOwner) {
            binding.textHome.text = it
        }
    }
}