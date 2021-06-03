package com.example.rostik.ui.home.contracts

import android.os.Bundle
import android.view.View
import com.example.rostik.R
import com.example.rostik.databinding.FragmentContractsBinding
import com.example.rostik.ui.App
import com.example.rostik.ui.core.BaseFragment

class ContractsFragment : BaseFragment() {
    override val titleToolbar = R.string.contracts

    override lateinit var layout: View

    private lateinit var binding: FragmentContractsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentContractsBinding.inflate(layoutInflater)
        App.appComponent.inject(this)
        layout = binding.root
    }
}