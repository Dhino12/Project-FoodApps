package com.example.foodapplication.ui.detail.food

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapplication.R
import com.example.foodapplication.core.util.VectorDrawableUtils
import com.example.foodapplication.databinding.ItemTimelineBinding

class TimelineAdapter(private val mStep:List<String>):RecyclerView.Adapter<TimelineAdapter.ViewHolder>() {

    inner class ViewHolder(itemView:View, viewType: Int):RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTimelineBinding.bind(itemView)
        val timelineView = binding.timeline
        init {
            timelineView.initLine(viewType)
        }
        fun bind(step:String){
            binding.textTimelineTitle.text = step
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mLayoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_timeline, parent, false)
        return ViewHolder(mLayoutInflater, viewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mStep[position])
        when{
            holder.itemViewType == 0 -> {
                setMarker(holder, R.drawable.ic_marker_inactive, R.color.orange)
            }
            else -> setMarker(holder, R.drawable.ic_marker_active, R.color.orange)
        }
    }

    private fun setMarker(holder: ViewHolder, drawableResId:Int, colorFilter:Int){
        holder.timelineView.marker = VectorDrawableUtils.getDrawable(holder.itemView.context, drawableResId, ContextCompat.getColor(holder.itemView.context, colorFilter))
    }

    override fun getItemCount(): Int = mStep.size
}