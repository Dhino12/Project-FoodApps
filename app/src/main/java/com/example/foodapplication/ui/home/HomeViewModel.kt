package com.example.foodapplication.ui.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.core.domain.usecase.FoodUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(foodUseCase: FoodUseCase) :ViewModel() {

    val cook = LiveDataReactiveStreams.fromPublisher(foodUseCase.getAllCooking())
    val article = LiveDataReactiveStreams.fromPublisher(foodUseCase.getAllArticle())

    val cookForTest = foodUseCase.getAllCooking()

}
