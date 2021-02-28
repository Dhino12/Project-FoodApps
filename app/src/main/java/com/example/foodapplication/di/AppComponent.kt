package com.example.foodapplication.di

import com.example.foodapplication.core.di.CoreComponent
import com.example.foodapplication.ui.category.ListCategoryFragment
import com.example.foodapplication.ui.category.contentCategory.ContentCategoryFragment
import com.example.foodapplication.ui.detail.food.DetailFoodActivity
import com.example.foodapplication.ui.favorite.FavoritesFragment
import com.example.foodapplication.ui.home.HomeFragment
import com.example.foodapplication.ui.search.SearchFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(coreComponent: CoreComponent):AppComponent
    }

    fun inject(fragment:HomeFragment)
    fun inject(fragment:FavoritesFragment)
    fun inject(fragment:ListCategoryFragment)
    fun inject(fragment:ContentCategoryFragment)
    fun inject(fragment:SearchFragment)
    fun inject(activity: DetailFoodActivity)

}