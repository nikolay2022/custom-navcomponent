package com.example.navcomponent.features.dashboard

import android.content.Context
import android.os.Bundle
import com.example.navcomponent.R
import com.example.navcomponent.base.BaseFragment
import com.example.navcomponent.base.DefaultArgs
import com.example.navcomponent.databinding.FragmentDashboardBinding
import com.example.navcomponent.utils.launchOnCreated
import com.example.navcomponent.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel, DefaultArgs>() {

    override val binding by viewBinding(FragmentDashboardBinding::bind)
    override val viewModel by lazy { getViewModel<DashboardViewModel>() }
    override val layoutRes: Int = R.layout.fragment_dashboard

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


