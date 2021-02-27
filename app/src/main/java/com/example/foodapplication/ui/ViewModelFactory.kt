package com.example.foodapplication.ui

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.foodapplication.core.data.CookingRepository
import com.example.foodapplication.core.di.Injection
import com.example.foodapplication.core.domain.usecase.FoodUseCase
import com.example.foodapplication.ui.category.ListCategoryViewModel
import com.example.foodapplication.ui.category.contentCategory.ContentCategoryViewModel
import com.example.foodapplication.ui.detail.food.DetailViewModel
import com.example.foodapplication.ui.favorite.FavoriteFoodViewModel
import com.example.foodapplication.ui.home.HomeViewModel
import com.example.foodapplication.ui.search.SearchViewModel

class ViewModelFactory private constructor(private val foodUseCase: FoodUseCase):ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var instance:ViewModelFactory? = null

        fun getInstance(context: Context, lifecycleOwner: LifecycleOwner? = null) : ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideFoodUseCase(context,lifecycleOwner))
            }
    }

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