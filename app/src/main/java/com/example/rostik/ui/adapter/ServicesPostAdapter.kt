package com.example.rostik.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rostik.databinding.ServicesViewItemBinding
import com.example.rostik.databinding.ServicesViewItemPostBinding
import com.example.rostik.domain.contracts.ServiceEntity

class ServicesPostAdapter: BaseAdapter<ServicesPostAdapter.ServicesPostViewHolder>() {

    private lateinit var binding: ServicesViewItemPostBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesPostViewHolder {
        binding = ServicesViewItemPostBinding.inflate(LayoutInflater.from(parent.context))
        val lp = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        binding.root.layoutParams = lp
        return ServicesPostViewHolder(binding.root, binding)
    }




    class ServicesPostViewHolder(view: View, binding: ServicesViewItemPostBinding): BaseAdapter.BaseViewHolder(view){
        private var binding: ServicesViewItemPostBinding


        init {
            this.binding = binding
        }

        override fun bind(item: Any) {
            let {
                item as ServiceEntity
                binding.tvServicesPostName.text = item.name
                binding.tvServicesPostType.text = item.type
                binding.tvServicesPostCost.text = item.cost.toString()+" руб."
                binding.checkboxServicesPost.isChecked = item.post
                binding.checkboxServicesPost.setOnCheckedChangeListener { buttonView, isChecked -> item.post = isChecked }
            }
        }
    }


}