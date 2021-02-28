package com.example.foodapplication.ui.search

import androidx.lifecycle.*
import com.example.foodapplication.core.data.Resource
import com.example.foodapplication.core.domain.model.Search
import com.example.foodapplication.core.domain.usecase.FoodUseCase
import javax.inject.Inject

class SearchViewModel @Inject constructor(foodUseCase: FoodUseCase):ViewModel() {
    private val querySearch = MutableLiveData<String>()
    fun setQuerySearch(query:String){
        this.querySearch.value = query
    }

    val search:LiveData<Resource<List<Search>>> = Transformations.switchMap(querySearch){
        query -> LiveDataReactiveStreams.fromPublisher(foodUseCase.getSearchFood(querySearch.value))
    }
}