package com.example.foodapplication.ui.favorite

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.core.domain.usecase.FoodUseCase
import javax.inject.Inject

class FavoriteFoodViewModel @Inject constructor(foodUseCase: FoodUseCase):ViewModel() {
    val favoriteData = LiveDataReactiveStreams.fromPublisher(foodUseCase.getFavoriteFood())
}