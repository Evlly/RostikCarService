package com.example.rostik.ui.home.contracts

import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rostik.EntryViewModel
import com.example.rostik.R
import com.example.rostik.databinding.FragmentContractsBinding
import com.example.rostik.databinding.FragmentEntryBinding
import com.example.rostik.domain.contracts.ContractEntity
import com.example.rostik.domain.contracts.ServiceEntity
import com.example.rostik.presentation.viewmodel.ContractsViewModel
import com.example.rostik.ui.App
import com.example.rostik.ui.adapter.ContractsAdapter
import com.example.rostik.ui.adapter.ServicesPostAdapter
import com.example.rostik.ui.core.BaseFragment
import com.example.rostik.ui.core.ext.onFailure
import com.example.rostik.ui.core.ext.onSuccess

class EntryFragment : BaseFragment() {

    override val titleToolbar = R.string.F

    override lateinit var layout: View

    private lateinit var binding: FragmentEntryBinding
    private lateinit var entryViewModel: ContractsViewModel
    protected lateinit var entryAdapter: ServicesPostAdapter
    private lateinit var list: List<ServiceEntity>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        binding = FragmentEntryBinding.inflate(layoutInflater)
        entryAdapter = ServicesPostAdapter()
        layout = binding.root

        entryViewModel = viewModel {
            onSuccess(servicesData, ::showServices)
            onFailure(failureData, ::handleFailure)
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.servicesList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }
        if(entryViewModel.getServices()!!.isEmpty()){
            services()
        }
        else{
            list = entryViewModel.getServices()!!
            entryAdapter.add(list)
            binding.servicesList.adapter = entryAdapter
        }


        binding.etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun filter(text: String){
        val filteredList: ArrayList<ServiceEntity> = ArrayList()
        list.forEach {
            if(it.name.toLowerCase().contains(text.toLowerCase())){
                filteredList.add(it)
            }
        }
        entryAdapter.filterList(filteredList)
    }


    private fun showServices(list: List<ServiceEntity>?){
        binding.progressBar.visibility=View.INVISIBLE
        this.list = list!!
        entryAdapter.add(this.list)
        binding.servicesList.adapter = entryAdapter
    }




    fun services(){
        binding.progressBar.visibility=View.VISIBLE
        entryViewModel.contracts()
    }

}