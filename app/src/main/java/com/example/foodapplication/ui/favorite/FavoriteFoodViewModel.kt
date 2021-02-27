package com.example.foodapplication.ui.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.foodapplication.core.data.CookingRepository
import com.example.foodapplication.core.domain.usecase.FoodUseCase

class FavoriteFoodViewModel(foodUseCase: FoodUseCase):ViewModel() {
    val favoriteData = LiveDataReactiveStreams.fromPublisher(foodUseCase.getFavoriteFood())
}