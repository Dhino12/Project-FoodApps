package com.example.core.di

import com.example.core.data.CookingRepository
import com.example.core.domain.repository.IFoodRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(cookingRepository: CookingRepository):IFoodRepository
}