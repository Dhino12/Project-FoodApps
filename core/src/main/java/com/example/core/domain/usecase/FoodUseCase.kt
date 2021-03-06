package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Article
import com.example.core.domain.model.Category
import com.example.core.domain.model.Cooking
import com.example.core.domain.model.Search
import io.reactivex.Flowable

interface FoodUseCase {
    fun getAllCooking():Flowable<Resource<List<Cooking>>>

    fun getDetailCooking(key:List<String>):Flowable<Resource<List<Cooking>>>

    fun getAllArticle():Flowable<Resource<List<Article>>>

    fun getArticleDetail(key:List<String>):Flowable<Resource<Article>>

    fun getListCategory():Flowable<Resource<List<Category>>>

    fun getAllContentCategory(tag:String):Flowable<Resource<List<Cooking>>>

    fun getFavoriteFood():Flowable<List<Cooking>>

    fun setFavoriteFood(food:Cooking, state:Boolean)

    fun getSearchFood(query:String?):Flowable<Resource<List<Search>>>
}