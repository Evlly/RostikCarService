package com.example.rostik.ui.home.contracts

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rostik.R
import com.example.rostik.databinding.FragmentContractsBinding
import com.example.rostik.domain.contracts.ContractEntity
import com.example.rostik.presentation.viewmodel.ContractsViewModel
import com.example.rostik.ui.App
import com.example.rostik.ui.adapter.ContractsAdapter
import com.example.rostik.ui.core.BaseFragment
import com.example.rostik.ui.core.ext.onFailure
import com.example.rostik.ui.core.ext.onSuccess

class ContractsFragment : BaseFragment() {
    override val titleToolbar = R.string.contracts

    override lateinit var layout: View

    private lateinit var binding: FragmentContractsBinding
    private lateinit var contractsViewModel: ContractsViewModel
    protected lateinit var contractsAdapter: ContractsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        binding = FragmentContractsBinding.inflate(layoutInflater)
        contractsAdapter = ContractsAdapter()
        layout = binding.root

        contractsViewModel = viewModel {
            onSuccess(contractsData, ::showContracts)
            onFailure(failureData, ::handleFailure)
        }
        contracts()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contractsList.apply {
            setHasFixedSize(true)
            layoutManager =LinearLayoutManager(context)
        }
    }

    private fun showContracts(list: List<ContractEntity>?){
        hideProgress()
        contractsAdapter.add(list!!)
        binding.contractsList.adapter = contractsAdapter
    }



    fun contracts(){
        showProgress()
        contractsViewModel.contracts()
    }
}