package com.example.foodapplication.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapplication.R
import com.example.foodapplication.core.data.source.local.entity.ArticleEntity
import com.example.foodapplication.core.domain.model.Article
import com.example.foodapplication.databinding.ItemArticleBinding

class ArticleAdapter:RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private var listData = ArrayList<Article>()
    var onItemClick: ((Article) -> Unit)? = null

    fun setData(newListData:List<Article>?){
        if(newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        private val binding = ItemArticleBinding.bind(itemView)
        fun bind(data:Article){
            with(binding){
                Glide.with(itemView.context)
                        .load(data.thumb)
                        .into(imgFoodArticle)
                tvTitleArticle.text = data.title
                tvTagsArticle.text = data.tags
                Log.e("articleData: ",listData.size.toString())
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}