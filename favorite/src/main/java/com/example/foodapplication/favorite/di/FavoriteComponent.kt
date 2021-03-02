package com.example.foodapplication.favorite.di

import com.example.core.di.CoreComponent
import com.example.foodapplication.di.AppModule
import com.example.foodapplication.favorite.ui.FavoritesFragment
import com.example.foodapplication.di.AppScope
import dagger.Component

@AppScope
@Component(
        dependencies = [CoreComponent::class],
        modules = [AppModule::class, ViewModelModuleFavorite::class]
)
interface FavoriteComponent{

    fun inject(fragment: FavoritesFragment)

}