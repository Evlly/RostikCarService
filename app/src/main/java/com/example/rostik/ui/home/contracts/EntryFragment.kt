package com.example.rostik.ui.home.contracts

import android.app.DatePickerDialog
import android.content.res.Configuration
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rostik.EntryViewModel
import com.example.rostik.R
import com.example.rostik.databinding.FragmentContractsBinding
import com.example.rostik.databinding.FragmentEntryBinding
import com.example.rostik.domain.contracts.ContractEntity
import com.example.rostik.domain.contracts.ServiceEntity
import com.example.rostik.domain.type.None
import com.example.rostik.presentation.viewmodel.ContractsViewModel
import com.example.rostik.ui.App
import com.example.rostik.ui.adapter.ContractsAdapter
import com.example.rostik.ui.adapter.ServicesPostAdapter
import com.example.rostik.ui.core.BaseFragment
import com.example.rostik.ui.core.ext.onFailure
import com.example.rostik.ui.core.ext.onSuccess
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.contracts.contract

class EntryFragment : BaseFragment() {

    override val titleToolbar = R.string.F

    override lateinit var layout: View

    private lateinit var binding: FragmentEntryBinding
    private lateinit var entryViewModel: ContractsViewModel
    protected lateinit var entryAdapter: ServicesPostAdapter
    private lateinit var list: List<ServiceEntity>
    private val calendar = Calendar.getInstance()
    private val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")

    val dateD = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        binding.etDate.setText(simpleDateFormat.format(calendar.time))
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        binding = FragmentEntryBinding.inflate(layoutInflater)
        entryAdapter = ServicesPostAdapter()
        layout = binding.root

        entryViewModel = viewModel {
            onSuccess(servicesData, ::showServices)
            onSuccess(postServicesData, ::contractAdded)
            onFailure(failureData, ::handleFailure)
        }

        binding.etDate.setOnClickListener {
           DatePickerDialog(requireContext(),
               dateD,
               calendar.get(Calendar.YEAR),
               calendar.get(Calendar.MONTH),
               calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        binding.btnEntry.setOnClickListener {
            val listIds = ArrayList<Int>()
            list.forEach {
                if(it.post) listIds.add(it.id)
            }
            entryViewModel.postServices(binding.etDate.text.toString(), listIds)
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
        }
        binding.servicesList.adapter = entryAdapter

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
    }

    private fun contractAdded(none: None?){
        Toast.makeText(context, "Запись оформлена", Toast.LENGTH_SHORT).show()
    }



    fun services(){
        binding.progressBar.visibility=View.VISIBLE
        entryViewModel.contracts()
    }

}