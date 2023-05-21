package com.example.uscreen.features.notifications.presentation

import android.content.Context
import android.os.Bundle
import com.example.uscreen.base.BaseFragment
import com.example.uscreen.base.DefaultArgs
import com.example.uscreen.databinding.FragmentNotificationsBinding
import com.example.uscreen.utils.launchOnStarted
import org.koin.androidx.viewmodel.ext.android.getViewModel

class NotificationsFragment : BaseFragment<FragmentNotificationsBinding, NotificationsViewModel, DefaultArgs>() {

    override val binding by lazy { FragmentNotificationsBinding.inflate(layoutInflater) }
    override val viewModel by lazy { getViewModel<NotificationsViewModel>() }

    override fun initView(viewBinding: FragmentNotificationsBinding, context: Context, savedInstanceState: Bundle?) {}

    override fun onViewModelAttached(viewModel: NotificationsViewModel) {
        viewModel.text.launchOnStarted(viewLifecycleOwner) {
            binding.textNotifications.text = it
        }
    }
}
