package com.example.navcomponent.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.navcomponent.R
import com.example.navcomponent.databinding.FragmentDashboardBinding
import com.example.navcomponent.utils.BaseFragment
import com.example.navcomponent.utils.DefaultArgs

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
            navigateTo(R.id.next_action)
        }

        return root
    }

}


