package com.example.foodapplication.core.data.source.remote.network

import com.example.foodapplication.core.data.source.remote.response.*
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/api/recipes/{page}")
    fun getCooking(@Path("page") page:Int): Flowable<CookingResponse>

    @GET("/api/categorys/article/{tags}")
    fun getArticle(@Path("tags") tags:String): Flowable<ArticleResponse>

    @GET("/api/recipe/{key}")
    fun getDetailCooking(@Path("key") key:String):Flowable<CookingDetailResponse>

    @GET("/api/article/{tags}/{key}")
    fun getDetailArticle(@Path("tags") tags:String, @Path("key") key:String):Flowable<ArticleDetailResponse>

    @GET("/api/categorys/recipes")
    fun getListCategory():Flowable<ListCategoryResponse>

    @GET("/api/categorys/recipes/{tag}")
    fun getCategoryContent(@Path("tag") key: String):Flowable<CookingResponse>

    @GET("/api/search/")
    fun getSearch(@Query("q") values:String):Flowable<SearchResponse>
}