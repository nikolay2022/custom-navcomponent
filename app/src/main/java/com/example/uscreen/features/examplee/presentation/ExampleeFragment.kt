package com.example.uscreen.features.examplee.presentation

import android.content.Context
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.uscreen.base.BaseFragment
import com.example.uscreen.base.DefaultArgs
import com.example.uscreen.databinding.FragmentExampleBinding
import com.example.uscreen.databinding.FragmentExampleeBinding
import com.example.uscreen.features.example.presentation.ExampleViewModel
import com.example.uscreen.utils.launchOnStarted
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ExampleeFragment : BaseFragment<FragmentExampleeBinding, ExampleeViewModel, DefaultArgs>() {

    override val binding by lazy { FragmentExampleeBinding.inflate(layoutInflater) }
    override val viewModel by lazy { getViewModel<ExampleeViewModel>() }

    override fun initView(
        viewBinding: FragmentExampleeBinding,
        context: Context,
        savedInstanceState: Bundle?
    ) {
    }

    override fun onViewModelAttached(viewModel: ExampleeViewModel) {
        viewModel.text.launchOnStarted(viewLifecycleOwner) {
            binding.textExample.text = it
        }
        binding.saveButton.setOnClickListener {
            viewModel.saveButton(binding.textInputEditText.text.toString())
        }
    }
}