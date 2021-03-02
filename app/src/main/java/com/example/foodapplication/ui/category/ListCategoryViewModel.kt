package com.example.foodapplication.ui.category

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.core.domain.usecase.FoodUseCase
import javax.inject.Inject

class ListCategoryViewModel @Inject constructor(foodUseCase: FoodUseCase):ViewModel() {
    val listCategory = LiveDataReactiveStreams.fromPublisher(foodUseCase.getListCategory())
}