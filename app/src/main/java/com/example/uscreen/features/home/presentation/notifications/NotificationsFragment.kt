package com.example.uscreen.features.home.presentation.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.uscreen.databinding.FragmentNotificationsBinding
import com.example.uscreen.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class NotificationsFragment : BaseFragment<FragmentNotificationsBinding, NotificationsViewModel>() {

    override val binding: FragmentNotificationsBinding
            by lazy { FragmentNotificationsBinding.inflate(layoutInflater) }

    override val viewModel: NotificationsViewModel by lazy { getViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
}
