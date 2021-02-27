package com.example.foodapplication.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapplication.R
import com.example.foodapplication.core.data.source.local.entity.CookingEntity
import com.example.foodapplication.core.domain.model.Cooking
import com.example.foodapplication.databinding.ItemRecentBinding

class CookingAdapter : RecyclerView.Adapter<CookingAdapter.ViewHolder>() {

    private var listData = ArrayList<Cooking>()
    var onItemClick: ((Cooking) -> Unit)? = null

    fun setData(newListData:List<Cooking>?){
        if(newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        private val binding = ItemRecentBinding.bind(itemView)
        fun bind(data:Cooking){
            with(binding){
                Glide.with(itemView.context)
                        .load(data.thumb)
                        .into(imgFood)
                tvTitleFood.text = data.title
                times.text = data.times
                dificulty.text = data.dificulty
                portion.text = data.servings
                Log.e("cookingTitle",data.title.toString())

            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recent, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}