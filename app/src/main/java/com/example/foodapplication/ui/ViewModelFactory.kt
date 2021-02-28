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
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider

@AppScope
class ViewModelFactory @Inject constructor(
        private val creators:Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
):ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull{
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
        return creator.get() as T
    }
}