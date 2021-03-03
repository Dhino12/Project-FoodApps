package com.example.foodapplication.random.di

import com.example.core.di.CoreComponent
import com.example.foodapplication.di.AppModule
import com.example.foodapplication.di.AppScope
import com.example.foodapplication.random.ui.RandomActivity
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModuleRandom::class]
)
interface RandomComponent{

    fun inject(activity: RandomActivity)

}