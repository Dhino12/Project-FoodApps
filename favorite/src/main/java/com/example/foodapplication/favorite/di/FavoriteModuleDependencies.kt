package com.example.foodapplication.favorite.di

import com.example.core.domain.usecase.FoodInteractor
import com.example.core.domain.usecase.FoodUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class FavoriteModuleDependencies {
    @Binds
    abstract fun providesFoodUseCase(foodInteractor: FoodInteractor): FoodUseCase
}