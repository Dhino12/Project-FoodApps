package com.example.foodapplication.ui.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.foodapplication.core.data.CookingRepository
import com.example.foodapplication.core.domain.usecase.FoodUseCase

class HomeViewModel(foodUseCase: FoodUseCase) :ViewModel() {

    val cook = LiveDataReactiveStreams.fromPublisher(foodUseCase.getAllCooking())
    val article = LiveDataReactiveStreams.fromPublisher(foodUseCase.getAllArticle())
}