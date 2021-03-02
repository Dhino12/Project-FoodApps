package com.example.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.databinding.ItemListBinding
import com.example.core.domain.model.Search

class SearchAdapter:RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private val listData = ArrayList<Search>()
    var onItemClicked: ((Search)-> Unit)? = null

    fun setData(newListData:List<Search>?){
        if(newListData.isNullOrEmpty()) {
            Log.e("error Search", newListData.isNullOrEmpty().toString())
            return
        }
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class SearchViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
        private val binding = ItemListBinding.bind(itemView)
        fun bind(dataSearch: Search){
            with(binding) {
                Glide.with(itemView.context)
                        .load(dataSearch.thumb)
                        .into(imgList)
                tvTitleList.text = dataSearch.title
                tvTimesList.text = dataSearch.times
                tvDificultyList.text = dataSearch.dificulty
                tvPortionList.text = dataSearch.portion
                tvTimesList.visibility = View.VISIBLE
                tvDificultyList.visibility = View.VISIBLE
                tvPortionList.visibility = View.VISIBLE
            }
            Log.e("error Search", dataSearch.dificulty.toString())
        }

        init {
            binding.root.setOnClickListener {
                onItemClicked?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

}