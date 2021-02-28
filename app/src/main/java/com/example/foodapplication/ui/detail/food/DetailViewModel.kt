package com.example.foodapplication.ui.detail.food

import android.util.Log
import androidx.lifecycle.*
import com.example.foodapplication.core.data.Resource
import com.example.foodapplication.core.domain.model.Cooking
import com.example.foodapplication.core.domain.usecase.FoodUseCase
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val foodUseCase:FoodUseCase):ViewModel() {
    private var titleCooking = MutableLiveData<List<String>>()
    private var titleArticle = MutableLiveData<List<String>>()

    fun setSelectedCook(titleCookingDb:List<String>? = null, titleArticleDb: List<String>? = null){

        Log.e("error contentTitleDb",titleCookingDb?.toList().toString())
        Log.e("error TitleContent", titleCookingDb?.size.toString())
        if(titleCookingDb.isNullOrEmpty()){
            this.titleArticle.value = titleArticleDb
        }else if(titleArticleDb.isNullOrEmpty()){
            this.titleCooking.value = titleCookingDb
        }
    }

    var cookingDetail:LiveData<Resource<List<Cooking>>> = Transformations.switchMap(titleCooking){
        title -> LiveDataReactiveStreams.fromPublisher(foodUseCase.getDetailCooking(title))
    }

    fun <T>foodDetail(getCook:Boolean):LiveData<Resource<T>>{
        return if(getCook){
            Log.e("error foodDetailVM TRUE",titleCooking.value.toString())
            LiveDataReactiveStreams.fromPublisher(foodUseCase.getDetailCooking(titleCooking.value!!)) as LiveData<Resource<T>>
        }else{
            LiveDataReactiveStreams.fromPublisher(foodUseCase.getArticleDetail(titleArticle.value!!)) as LiveData<Resource<T>>
        }
    }

    fun setFavoriteFood(food:Cooking, newState:Boolean) = foodUseCase.setFavoriteFood(food, newState)
}