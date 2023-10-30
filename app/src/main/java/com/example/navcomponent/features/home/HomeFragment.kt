package com.example.navcomponent.features.home

import android.content.Context
import android.os.Bundle
import com.example.navcomponent.R
import com.example.navcomponent.base.BaseFragment
import com.example.navcomponent.base.DefaultArgs
import com.example.navcomponent.databinding.FragmentHomeBinding
import com.example.navcomponent.utils.launchOnStarted
import com.example.navcomponent.utils.viewBinding
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