package com.example.uscreen.features.home.presentation.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.uscreen.R
import com.example.uscreen.databinding.FragmentDashboardBinding
import com.example.uscreen.utils.BaseFragment
import com.example.uscreen.utils.DefaultArgs
import org.koin.androidx.viewmodel.ext.android.getViewModel


class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>() {
    override val binding: FragmentDashboardBinding
            by lazy { FragmentDashboardBinding.inflate(layoutInflater) }

    override val viewModel: DashboardViewModel by lazy { getViewModel() }

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
            navigateTo(R.id.next_action, DefaultArgs(true))
        }

        return root
    }
}


