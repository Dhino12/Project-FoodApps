package com.example.foodapplication.ui.category.contentCategory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapplication.R
import com.example.foodapplication.core.domain.model.Cooking
import com.example.foodapplication.databinding.ItemListBinding

class ContentCategoryAdapter : RecyclerView.Adapter<ContentCategoryAdapter.ContentViewHolder>() {

    private val contentCategory = ArrayList<Cooking>()
    var onItemClickContent: ((Cooking) -> Unit)? = null

    fun setData(newListContent:List<Cooking>?){
        if(newListContent == null) return
        contentCategory.clear()
        contentCategory.addAll(newListContent)
        notifyDataSetChanged()
    }

    inner class ContentViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        private val binding = ItemListBinding.bind(itemView)
        fun bind(data:Cooking){
            with(binding){
                Glide.with(itemView.context)
                        .load(data.thumb)
                        .into(imgList)
                tvTitleList.text = data.title
                tvTimesList.text = data.times
                tvDificultyList.text = data.dificulty
                tvPortionList.text = data.servings

                tvTimesList.visibility = View.VISIBLE
                tvDificultyList.visibility = View.VISIBLE
                tvPortionList.visibility = View.VISIBLE
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClickContent?.invoke(contentCategory[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(contentCategory[position])
    }

    override fun getItemCount(): Int = contentCategory.size
}