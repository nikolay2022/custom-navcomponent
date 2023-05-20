package com.example.uscreen.features.home.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.uscreen.databinding.FragmentHomeBinding
import com.example.uscreen.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val binding: FragmentHomeBinding
            by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    override val viewModel: HomeViewModel by lazy { getViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = binding.root

        val textView: TextView = binding.textHome
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }
}
