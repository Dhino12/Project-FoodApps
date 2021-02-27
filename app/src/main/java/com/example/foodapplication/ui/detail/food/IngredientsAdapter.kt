package com.example.foodapplication.ui.detail.food

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapplication.R
import com.example.foodapplication.databinding.ItemIngredientsBinding
import kotlinx.android.synthetic.main.item_ingredients.view.*

class IngredientsAdapter(private val mIngredients:List<String>):RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {
    class IngredientsViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){
        private val binding = ItemIngredientsBinding.bind(itemView)
        fun bind(ingredients:String){
            with(itemView){
                binding.textIngredients.text = ingredients
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredients, parent, false)
        return IngredientsViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.bind(mIngredients[position])
    }

    override fun getItemCount(): Int = mIngredients.size
}