package com.ecoliving.mobile.presentation.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ecoliving.mobile.data.remote.response.MealsItem
import com.example.ecoliving.databinding.HistroryCardBinding

class MealsAdapter(private var mealsList: List<MealsItem>) : RecyclerView.Adapter<MealsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = HistroryCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mealItem = mealsList[position]
        holder.bind(mealItem)
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    fun updateMealsList(newMealsList: List<MealsItem>) {
        mealsList = newMealsList
        notifyDataSetChanged()
    }

    class MyViewHolder(private val binding: HistroryCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MealsItem) {
            binding.titleHistory.text = data.type
            binding.carbonHistory.text = data.carbonEmission
        }
    }
}