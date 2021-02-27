package com.example.foodapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodapplication.core.domain.usecase.FoodUseCase
import com.example.foodapplication.di.AppScope
import com.example.foodapplication.ui.category.ListCategoryViewModel
import com.example.foodapplication.ui.category.contentCategory.ContentCategoryViewModel
import com.example.foodapplication.ui.detail.food.DetailViewModel
import com.example.foodapplication.ui.favorite.FavoriteFoodViewModel
import com.example.foodapplication.ui.home.HomeViewModel
import com.example.foodapplication.ui.search.SearchViewModel
import javax.inject.Inject

@AppScope
class ViewModelFactory @Inject constructor(private val foodUseCase: FoodUseCase):ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when{
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(foodUseCase) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(foodUseCase) as T
            }
            modelClass.isAssignableFrom(ListCategoryViewModel::class.java) -> {
                ListCategoryViewModel(foodUseCase) as T
            }
            modelClass.isAssignableFrom(ContentCategoryViewModel::class.java) -> {
                ContentCategoryViewModel(foodUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteFoodViewModel::class.java) -> {
                FavoriteFoodViewModel(foodUseCase) as T
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(foodUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)
        }

}