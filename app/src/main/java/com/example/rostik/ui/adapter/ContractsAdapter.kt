package com.example.rostik.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rostik.databinding.ContractsViewItemBinding
import com.example.rostik.domain.contracts.ContractEntity

class ContractsAdapter : BaseAdapter<ContractsAdapter.ContractsViewHolder>() {
    private lateinit var binding: ContractsViewItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContractsViewHolder {
        binding = ContractsViewItemBinding.inflate(LayoutInflater.from(parent.context))
        val lp = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        binding.root.layoutParams = lp
        return ContractsViewHolder(binding.root, binding)
    }

    class ContractsViewHolder(view: View, binding: ContractsViewItemBinding): BaseAdapter.BaseViewHolder(view){
        private var binding: ContractsViewItemBinding
        protected var servicesAdapter: ServicesAdapter


        init {
            this.binding = binding
            servicesAdapter = ServicesAdapter()
        }

        override fun bind(item: Any) {
            let {
                item as ContractEntity
                binding.tvContractsDateStart.text = item.date_start
                binding.tvContractsDateFinish.text = item.date_finish
                binding.tvContractsStatus.text = item.name
                binding.tvContractsMechanic.text = item.fio_small
                binding.recyclerServicesContracts.apply {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context)
                    adapter = servicesAdapter
                }
                servicesAdapter.add(item.services)
            }
        }

    }

}