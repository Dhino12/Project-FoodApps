package com.example.foodapplication.ui.category.contentCategory

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.foodapplication.core.data.CookingRepository
import com.example.foodapplication.core.domain.usecase.FoodUseCase
import javax.inject.Inject

class ContentCategoryViewModel @Inject constructor(foodUseCase: FoodUseCase):ViewModel() {
    private val tagContent = MutableLiveData<String>()

    fun setSelectedCategory(tags:String?){
        this.tagContent.value = tags
    }

    val contentCategory = Transformations.switchMap(tagContent){
        tag -> LiveDataReactiveStreams.fromPublisher(foodUseCase.getAllContentCategory(tag))
    }
}