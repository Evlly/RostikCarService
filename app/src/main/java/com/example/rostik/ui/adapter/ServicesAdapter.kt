package com.example.rostik.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rostik.databinding.ServicesViewItemBinding
import com.example.rostik.domain.contracts.ServiceEntity

class ServicesAdapter: BaseAdapter<ServicesAdapter.ServicesViewHolder>() {

    private lateinit var binding: ServicesViewItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder {
        binding = ServicesViewItemBinding.inflate(LayoutInflater.from(parent.context))
        val lp = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        binding.root.layoutParams = lp
        return ServicesViewHolder(binding.root, binding)
    }


    class ServicesViewHolder(view: View, binding: ServicesViewItemBinding): BaseAdapter.BaseViewHolder(view){
        private var binding: ServicesViewItemBinding


        init {
            this.binding = binding
        }

        override fun bind(item: Any) {
            let {
                item as ServiceEntity
                binding.tvServicesName.text = item.name
                binding.tvServicesCost.text = item.cost.toString()
            }
        }
    }


}