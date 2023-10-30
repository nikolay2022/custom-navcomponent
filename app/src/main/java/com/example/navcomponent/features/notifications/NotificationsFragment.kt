package com.example.navcomponent.features.notifications

import android.content.Context
import android.os.Bundle
import com.example.navcomponent.R
import com.example.navcomponent.base.BaseFragment
import com.example.navcomponent.base.DefaultArgs
import com.example.navcomponent.databinding.FragmentNotificationsBinding
import com.example.navcomponent.utils.launchOnStarted
import com.example.navcomponent.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class NotificationsFragment : BaseFragment<FragmentNotificationsBinding, NotificationsViewModel, DefaultArgs>() {

    override val binding by viewBinding(FragmentNotificationsBinding::bind)
    override val viewModel by lazy { getViewModel<NotificationsViewModel>() }
    override val layoutRes: Int = R.layout.fragment_notifications

    override fun initView(viewBinding: FragmentNotificationsBinding, context: Context, savedInstanceState: Bundle?) {}

    override fun onViewModelAttached(viewModel: NotificationsViewModel) {
        viewModel.text.launchOnStarted(viewLifecycleOwner) {
            binding.textNotifications.text = it
        }
    }
}
