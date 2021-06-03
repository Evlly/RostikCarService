package com.example.rostik

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rostik.databinding.FragmentInfoBinding
import com.example.rostik.presentation.viewmodel.ViewModelFactory
import com.example.rostik.ui.core.BaseFragment

class InfoFragment : Fragment()
{

    companion object {
        fun newInstance() = InfoFragment()
    }
    private lateinit var binding: FragmentInfoBinding

    private var infoViewModel = InfoViewModel()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.buttonCall.setOnClickListener {
            infoViewModel.call(requireContext())
        }
    }
}