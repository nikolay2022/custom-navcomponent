package com.example.uscreen.features.detail

import android.content.Context
import android.os.Bundle
import com.example.uscreen.R
import com.example.uscreen.base.BaseFragment
import com.example.uscreen.base.DefaultArgs
import com.example.uscreen.databinding.FragmentExampleBinding
import com.example.uscreen.utils.launchOnStarted
import com.example.uscreen.utils.viewBinding
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