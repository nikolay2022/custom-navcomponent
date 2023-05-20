package com.example.uscreen.features.home.presentation.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.uscreen.databinding.FragmentExampleBinding
import com.example.uscreen.features.home.presentation.dashboard.DashboardViewModel
import com.example.uscreen.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ExampleFragment : BaseFragment<FragmentExampleBinding, ExampleViewModel>() {
    override val binding: FragmentExampleBinding
            by lazy { FragmentExampleBinding.inflate(layoutInflater) }

    override val viewModel: ExampleViewModel by lazy { getViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val root: View = binding.root

        val textView: TextView = binding.textExample
        viewModel.text.observe(viewLifecycleOwner) {
            //возможно тут надо переключить активнй таб
            textView.text = it
        }

        return root
    }
}