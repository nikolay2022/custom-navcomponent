package com.example.navcomponent.features.detail

import android.content.Context
import android.os.Bundle
import com.example.navcomponent.R
import com.example.navcomponent.base.BaseFragment
import com.example.navcomponent.base.DefaultArgs
import com.example.navcomponent.databinding.FragmentExampleBinding
import com.example.navcomponent.utils.launchOnStarted
import com.example.navcomponent.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class DetailFragment : BaseFragment<FragmentExampleBinding, DetailViewModel, DefaultArgs>() {

    override val binding by viewBinding(FragmentExampleBinding::bind)
    override val viewModel by lazy { getViewModel<DetailViewModel>() }
    override val layoutRes: Int = R.layout.fragment_example

    override fun initView(viewBinding: FragmentExampleBinding, context: Context, savedInstanceState: Bundle?) {}

    override fun onViewModelAttached(viewModel: DetailViewModel) {
        viewModel.text.launchOnStarted(viewLifecycleOwner) {
            binding.textExample.text = it
        }
    }
}