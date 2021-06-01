package com.example.rostik

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rostik.databinding.FragmentInfoBinding
import com.example.rostik.ui.core.BaseFragment

class InfoFragment : BaseFragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }
    override lateinit var layout: View
    private lateinit var binding: FragmentInfoBinding

    private lateinit var infoViewModel: InfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentInfoBinding.inflate(layoutInflater)
        layout = binding.root
        infoViewModel = viewModel {  }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.buttonCall.setOnClickListener {
            infoViewModel.call(requireContext())
        }
    }
}