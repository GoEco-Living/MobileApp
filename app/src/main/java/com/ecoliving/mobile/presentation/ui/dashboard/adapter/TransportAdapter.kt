package com.ecoliving.mobile.presentation.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ecoliving.mobile.data.remote.response.TransportItem
import com.example.ecoliving.databinding.HistroryCardBinding

class TransportAdapter(private var transportList: List<TransportItem>) : RecyclerView.Adapter<TransportAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = HistroryCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mealItem = transportList[position]
        holder.bind(mealItem)
    }

    override fun getItemCount(): Int {
        return transportList.size
    }

    fun updateTransportList(newMealsList: List<TransportItem>) {
        transportList = newMealsList
        notifyDataSetChanged()
    }

    class MyViewHolder(private val binding: HistroryCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TransportItem) {
            binding.titleHistory.text = data.type
            binding.carbonHistory.text = data.carbonEmission
        }
    }
}