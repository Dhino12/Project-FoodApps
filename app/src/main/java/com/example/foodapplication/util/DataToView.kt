package com.example.foodapplication.util

import android.content.Context
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.foodapplication.R
import com.example.core.domain.model.Article
import com.example.core.domain.model.Cooking
import com.example.core.ui.IngredientsAdapter
import com.example.core.ui.TimelineAdapter
import com.example.foodapplication.databinding.ActivityDetailFoodBinding
import com.example.foodapplication.ui.detail.food.DetailViewModel
import com.github.ksoichiro.android.observablescrollview.ScrollUtils
import com.nineoldandroids.view.ViewHelper

class DataToView(
        private var binding:ActivityDetailFoodBinding,
        private var context:Context,
        private var viewModel:DetailViewModel
) {
    private lateinit var adaptersTL: TimelineAdapter
    private lateinit var adapaterIngredients: IngredientsAdapter

    companion object{
        private val instance: DataToView? = null

        fun getInstance(
                binding:ActivityDetailFoodBinding,
                context:Context,
                viewModel:DetailViewModel
        ): DataToView =
                instance ?: synchronized(this){
                    instance
                        ?: DataToView(
                            binding,
                            context,
                            viewModel
                        )
                }
    }

    fun implementToView(
            cookingDetailEntity: Cooking? = null,
            articleDetailEntity: Article? = null
    ){

        if(cookingDetailEntity != null){
            Glide.with(context)
                    .load(cookingDetailEntity.thumb)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.imgFoodDetail)

            binding.titleDetail.text = cookingDetailEntity.title
            binding.servingsContentDetail.text = cookingDetailEntity.servings
            binding.timesContentDetail.text = cookingDetailEntity.times
            binding.dificultyContentDetail.text = cookingDetailEntity.dificulty
            val i = cookingDetailEntity.step!!.split(".,")
            val j = cookingDetailEntity.ingredient!!.removeSurrounding("[", "]").split(",,")

            setTLRecyclerView(i)
            setIngRecyclerView(j)

            Log.e("error DataToView cookDetail",cookingDetailEntity.title.toString())
            var stateFavorite = cookingDetailEntity.isFavorite
            if (stateFavorite != null) {
                setStatusFavorite(stateFavorite)
                binding.btnFavorite.setOnClickListener {
                    stateFavorite = !stateFavorite!!
                    viewModel.setFavoriteFood(cookingDetailEntity, stateFavorite!!)
                    setStatusFavorite(stateFavorite!!)
                }
            }

        }else{
            binding.rvIngredients.visibility = View.GONE
            binding.tvChangeStep.visibility = View.GONE
            binding.rvStep.visibility = View.GONE
            binding.cvItemCooking.visibility = View.GONE

            Glide.with(context)
                    .load(articleDetailEntity?.thumb)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.imgFoodDetail)
            binding.titleDetail.text = articleDetailEntity?.title
            binding.tvChangeIngredients.text = context.getString(R.string.description)
            binding.tvAuthor.text = articleDetailEntity?.author
            binding.tvDesc.visibility = View.VISIBLE
            binding.tvDesc.text = articleDetailEntity?.description
        }
    }

    private fun setIngRecyclerView(ingre: List<String>){
        with(binding.rvIngredients){
            adapaterIngredients = IngredientsAdapter(ingre)
            binding.rvIngredients.layoutManager = LinearLayoutManager(context)
            binding.rvIngredients.setHasFixedSize(true)
            binding.rvIngredients.adapter = adapaterIngredients
        }
    }

    private fun setTLRecyclerView(step: List<String>){
        with(binding.rvStep){
            adaptersTL = TimelineAdapter(step)
            binding.rvStep.layoutManager = LinearLayoutManager(context)
            binding.rvStep.setHasFixedSize(true)
            binding.rvStep.adapter = adaptersTL
        }
    }

    private fun setStatusFavorite(state:Boolean){
        if(state){
            binding.btnFavorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_favorite_24))
        }else{
            binding.btnFavorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_favorite_border_24))
        }
    }

    fun updateFlexibleView(scrollY: Int){
        var mActionBarSize = getActionBarSize()
        var mFlexibleSpaceImageHeight = context.resources.getDimensionPixelSize(R.dimen.flexible_image_height_detail)

        val flexibleRange = mFlexibleSpaceImageHeight - mActionBarSize.toFloat()
        val minOverlayTransitionY = mActionBarSize - binding.overlay.height
        val minActionBarOverlayTransitionY = mActionBarSize - binding.toolbarOverlay.height
        val minStatusBarOverlayTransitionY = mActionBarSize - binding.statusBarCustom.height

        ViewHelper.setTranslationY(binding.overlay, ScrollUtils.getFloat(-scrollY.toFloat(), minOverlayTransitionY.toFloat(), 0f))
        ViewHelper.setTranslationY(binding.imgFoodDetail, ScrollUtils.getFloat(-scrollY / 2.toFloat(), minOverlayTransitionY.toFloat(), 0f))
        ViewHelper.setTranslationY(binding.toolbarOverlay, ScrollUtils.getFloat(-scrollY.toFloat(), minActionBarOverlayTransitionY.toFloat(), 0f))
        ViewHelper.setTranslationY(binding.statusBarCustom, ScrollUtils.getFloat(-scrollY.toFloat(), minStatusBarOverlayTransitionY.toFloat(), 0f))

        ViewHelper.setAlpha(binding.overlay, ScrollUtils.getFloat(scrollY.toFloat() / flexibleRange, 0f, 1f))
        ViewHelper.setAlpha(binding.toolbarOverlay, ScrollUtils.getFloat(scrollY.toFloat() / flexibleRange, 0f, 1f))
        ViewHelper.setAlpha(binding.statusBarCustom, ScrollUtils.getFloat(scrollY.toFloat() / flexibleRange, 0f, 1f))
    }

    private fun getActionBarSize():Int{
        val typedValue = TypedValue()
        val textSizeAttr = intArrayOf(R.attr.actionBarSize)
        val indexOfAttrTextSize = 0
        val a = context.obtainStyledAttributes(typedValue.data, textSizeAttr)
        val actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1)
        a.recycle()
        return actionBarSize
    }
}