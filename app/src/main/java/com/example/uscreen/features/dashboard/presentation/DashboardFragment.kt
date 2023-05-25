package com.example.uscreen.features.dashboard.presentation

import android.content.Context
import android.os.Bundle
import androidx.navigation.ui.NavigationUI
import com.example.uscreen.R
import com.example.uscreen.base.BaseFragment
import com.example.uscreen.base.DefaultArgs
import com.example.uscreen.databinding.FragmentDashboardBinding
import com.example.uscreen.utils.launchOnCreated
import org.koin.androidx.viewmodel.ext.android.getViewModel


class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel, DefaultArgs>() {

    override val binding by lazy { FragmentDashboardBinding.inflate(layoutInflater) }
    override val viewModel by lazy { getViewModel<DashboardViewModel>() }

    override fun initView(viewBinding: FragmentDashboardBinding, context: Context, savedInstanceState: Bundle?) {
        binding.btn.setOnClickListener {
            navigateTo(R.id.next_action, DefaultArgs(false))
        }
    }

    override fun onViewModelAttached(viewModel: DashboardViewModel) {
        viewModel.text.launchOnCreated(viewLifecycleOwner) {
            binding.textDashboard.text = it
        }
    }
}


