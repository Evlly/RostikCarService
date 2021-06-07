package com.example.rostik.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rostik.domain.contracts.ServiceEntity

abstract class BaseAdapter<VH: BaseAdapter.BaseViewHolder>: RecyclerView.Adapter<VH>() {

    var items = ArrayList<Any>()

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    fun getItem(position: Int): Any{
        return items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }


    fun add(newItem: Any){
        items.add(newItem)
    }

    fun add(newItems: List<Any>){
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun getList() = items

    fun filterList (filteredList: List<Any>){
        items = filteredList as ArrayList<Any>
        notifyDataSetChanged()
    }

    abstract class BaseViewHolder(protected val view: View) : RecyclerView.ViewHolder(view){
        abstract fun bind(item: Any)
    }
}