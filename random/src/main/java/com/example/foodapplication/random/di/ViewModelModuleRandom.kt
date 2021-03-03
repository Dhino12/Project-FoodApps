package com.example.foodapplication.random.di

import androidx.lifecycle.ViewModel
import com.example.foodapplication.di.ViewModelKey
import com.example.foodapplication.random.ui.RandomViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModuleRandom {
    @Binds
    @IntoMap
    @ViewModelKey(RandomViewModel::class)
    abstract fun bindRandomViewModel(viewModel: RandomViewModel):ViewModel
}