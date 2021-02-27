package com.example.foodapplication.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.foodapplication.core.data.source.local.entity.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface CookingDao {

    // Cooking Entity ========================

    @Query("SELECT * FROM cooking")
    fun getAllCooking():Flowable<List<CookingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCooking(cook:List<CookingEntity>):Completable

    @Query("SELECT * FROM cookingDetail WHERE title = :titleCook")
    fun getDetailCooking(titleCook:String):Flowable<List<CookingDetailEntity>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCookingDetail(cookData:CookingDetailEntity):Completable

    @Query("SELECT * FROM cookingDetail WHERE isFavorite = 1")
    fun getFavoriteCook():Flowable<List<CookingDetailEntity>>

    @Update
    fun updateFavoriteCook(cook:CookingDetailEntity)

    // Article Entity ========================

    @Query("SELECT * FROM article")
    fun getAllArticle():Flowable<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(cook:List<ArticleEntity>):Completable

    @Query("SELECT * FROM articleDetail WHERE title = :titleArticle")
    fun getDetailArticle(titleArticle:String):Flowable<ArticleDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticleDetail(articleData:ArticleDetailEntity):Completable

    // Category Entity ===========================

    @Query("SELECT * FROM listCategory")
    fun getAllListCategory():Flowable<List<ListCategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListCategory(listCategoryData: List<ListCategoryEntity>):Completable

    @Query("SELECT * FROM cooking WHERE tags = :tag")
    fun getContentCategory(tag:String):Flowable<List<CookingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContentCategory(listContentCategory:List<CookingEntity>):Completable


}