package com.example.uscreen.features.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.uscreen.databinding.FragmentExampleBinding
import com.example.uscreen.databinding.FragmentNotificationsBinding

class ExampleFragment : Fragment() {

    private var _binding: FragmentExampleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(ExampleViewModel::class.java)

        _binding = FragmentExampleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textExample
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            //возможно тут надо переключить активнй таб
            textView.text = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}