package com.example.uscreen.features.notifications

import android.content.Context
import android.os.Bundle
import com.example.uscreen.R
import com.example.uscreen.base.BaseFragment
import com.example.uscreen.base.DefaultArgs
import com.example.uscreen.databinding.FragmentNotificationsBinding
import com.example.uscreen.utils.launchOnStarted
import com.example.uscreen.utils.viewBinding
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
