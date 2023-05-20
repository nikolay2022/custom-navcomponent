package com.example.uscreen.features.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.uscreen.R
import com.example.uscreen.databinding.FragmentDashboardBinding
import com.example.uscreen.databinding.FragmentHomeBinding
import com.example.uscreen.utils.BaseFragment
import com.example.uscreen.utils.DefaultArgs


class DashboardFragment :
    BaseFragment<FragmentDashboardBinding, DashboardViewModel, DefaultArgs>() {
    override val binding: FragmentDashboardBinding
        by lazy { FragmentDashboardBinding.inflate(layoutInflater) }

    override val viewModel: DashboardViewModel
        by lazy { DashboardViewModel()  }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.btn.setOnClickListener {
            //TODO: передача параметров
            navigateTo(R.id.next_action)
        }

        return root
    }

    companion object {
        fun newInstance(): ExampleFragment =
            ExampleFragment().withArgs(
                DefaultArgs(true)
            )

    }
}


