package com.example.foodapplication.favorite.di

import androidx.lifecycle.ViewModel
import com.example.foodapplication.favorite.ui.FavoriteFoodViewModel
import com.example.foodapplication.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModuleFavorite {
    @Binds
    @IntoMap
    @ViewModelKey(FavoriteFoodViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: FavoriteFoodViewModel):ViewModel
}