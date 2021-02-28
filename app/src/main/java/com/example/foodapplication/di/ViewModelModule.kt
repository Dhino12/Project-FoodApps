package com.example.foodapplication.di

import androidx.lifecycle.ViewModel
import com.example.foodapplication.ui.category.ListCategoryViewModel
import com.example.foodapplication.ui.category.contentCategory.ContentCategoryViewModel
import com.example.foodapplication.ui.detail.food.DetailViewModel
import com.example.foodapplication.ui.favorite.FavoriteFoodViewModel
import com.example.foodapplication.ui.home.HomeViewModel
import com.example.foodapplication.ui.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteFoodViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: FavoriteFoodViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListCategoryViewModel::class)
    abstract fun bindListCategoryViewModel(viewModel: ListCategoryViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContentCategoryViewModel::class)
    abstract fun bindListContentCategoryViewModel(viewModel: ContentCategoryViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel):ViewModel

}