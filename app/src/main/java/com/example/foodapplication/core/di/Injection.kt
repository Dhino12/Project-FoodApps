package com.example.foodapplication.core.di

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.example.foodapplication.core.data.CookingRepository
import com.example.foodapplication.core.data.source.local.LocalDataSource
import com.example.foodapplication.core.data.source.local.room.CookingDatabase
import com.example.foodapplication.core.data.source.remote.RemoteDataSource
import com.example.foodapplication.core.data.source.remote.network.ApiConfig
import com.example.foodapplication.core.domain.usecase.FoodInteractor
import com.example.foodapplication.core.domain.usecase.FoodUseCase
import com.example.foodapplication.core.domain.repository.IFoodRepository
import com.example.foodapplication.core.util.AppExecutors

object Injection {
    private fun provideRepository(context: Context, lifecycleOwner: LifecycleOwner?): IFoodRepository {
        val database = CookingDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.cookingDao())
        val appExecutors = AppExecutors()
        return CookingRepository.getInstance(remoteDataSource, localDataSource, appExecutors, lifecycleOwner)
    }

    fun provideFoodUseCase(context:Context,lifecycleOwner: LifecycleOwner?):FoodUseCase{
        val repository = provideRepository(context,lifecycleOwner)
        return FoodInteractor(repository)
    }
}