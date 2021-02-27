package com.example.foodapplication.core.data.source.local

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.foodapplication.core.data.source.local.entity.*
import com.example.foodapplication.core.data.source.local.room.CookingDao
import com.example.foodapplication.core.data.source.remote.network.ApiResponse
import com.example.foodapplication.core.data.source.remote.response.ResultsDetailCooking
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class LocalDataSource private constructor(private val cookingDao:CookingDao){
    companion object{
        private val INSTANCE : LocalDataSource? = null

        fun getInstance(cookingDao: CookingDao):LocalDataSource =
                INSTANCE ?: synchronized(this){
                    INSTANCE ?: LocalDataSource(cookingDao)
                }
    }

    // =========== Cooking ===========
    fun getAllCooking():Flowable<List<CookingEntity>> = cookingDao.getAllCooking()

    fun insertCooking(cookingList:List<CookingEntity>) = cookingDao.insertCooking(cookingList)

    fun getDetailCooking(title:String):Flowable<List<CookingDetailEntity>> = cookingDao.getDetailCooking(title)

    fun insertDetailCooking(cookData:CookingDetailEntity) = cookingDao.insertCookingDetail(cookData)

    fun getFavoriteFood():Flowable<List<CookingDetailEntity>> = cookingDao.getFavoriteCook()

    fun setFavoriteFood(cooking:CookingDetailEntity, newState:Boolean){
        cooking.isFavorite = newState
        cookingDao.updateFavoriteCook(cooking)
    }

    // =========== Article ===========
    fun getAllArticle():Flowable<List<ArticleEntity>> = cookingDao.getAllArticle()

    fun insertArticle(articleList:List<ArticleEntity>) = cookingDao.insertArticle(articleList)

    fun insertDetailArticle(articleData:ArticleDetailEntity) = cookingDao.insertArticleDetail(articleData)

    fun getDetailArticle(title:String):Flowable<ArticleDetailEntity> = cookingDao.getDetailArticle(title)

    // =========== Category ===========
    fun getAllListCategory():Flowable<List<ListCategoryEntity>> = cookingDao.getAllListCategory()

    fun insertListCategory(listCategory:List<ListCategoryEntity>) = cookingDao.insertListCategory(listCategory)

    fun getAllContentCategory(tag:String):Flowable<List<CookingEntity>> = cookingDao.getContentCategory(tag)

    fun insertAllContentCategory(content:List<CookingEntity>) = cookingDao.insertContentCategory(content)
}