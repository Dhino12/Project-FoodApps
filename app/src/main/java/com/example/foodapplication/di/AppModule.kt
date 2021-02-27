package com.example.foodapplication.di

import com.example.foodapplication.core.domain.usecase.FoodInteractor
import com.example.foodapplication.core.domain.usecase.FoodUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideFoodUseCase(foodInteractor: FoodInteractor):FoodUseCase
}