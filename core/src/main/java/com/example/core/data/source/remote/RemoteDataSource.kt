package com.example.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@SuppressLint("CheckResult")
@Singleton
class RemoteDataSource @Inject constructor(private val apiService:ApiService) {

    fun getAllCooking():Flowable<ApiResponse<List<ResultsItemCooking>>>{
        val resultData = PublishSubject.create<ApiResponse<List<ResultsItemCooking>>>()

        val client = apiService.getCooking(1)
        client.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({
                    response -> val cookArray = response.results
                    if (cookArray != null) {
                        resultData.onNext(
                                (if(cookArray.isNotEmpty()) ApiResponse.Success(cookArray)
                                else ApiResponse.Empty))
                    }
                }, {
                    error -> resultData.onNext(ApiResponse.Error(error.message.toString()))
                    Log.e("error_RemoteDataSource",error.message.toString())
                })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getDetailCooking(titleKey:String):Flowable<ApiResponse<List<ResultsDetailCooking>>>{
        val resultData = PublishSubject.create<ApiResponse<List<ResultsDetailCooking>>>()


        val client = apiService.getDetailCooking(titleKey)
        client.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({
                    response ->
                    Log.e("errorMapper",response.results.toString())
                    if(response != null){
                        val listData:List<ResultsDetailCooking> = listOf(response.results!!)
                        resultData.onNext(
                                if(response != null) ApiResponse.Success(listData) else ApiResponse.Empty
                        )
                    }
                }, {
                    error -> resultData.onNext(ApiResponse.Error(error.message.toString()))
                    Log.e("error_RemoteDataSource DetailCooking", error.message.toString())
                })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getAllArticle():Flowable<ApiResponse<List<ResultItemArticle>>>{
        val resultData = PublishSubject.create<ApiResponse<List<ResultItemArticle>>>()
        val listCategory = arrayListOf("inspirasi-dapur","makanan-gaya-hidup","tips-masak")

        val client = apiService.getArticle(listCategory.random())
        client.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe( {
                    response -> val articleArray = response.results
                    if (articleArray != null) {
                        resultData.onNext(
                                if(articleArray.isNotEmpty()) ApiResponse.Success(articleArray)
                                else ApiResponse.Empty
                        )
                    }
                },{
                    error -> resultData.onNext(ApiResponse.Error(error.message.toString()))
                    Log.e("error_RemoteDataSource Article", error.message.toString())
                })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getDetailArticle(titleKey: String):Flowable<ApiResponse<ResultDetailArticle>>{
        val resultData = PublishSubject.create<ApiResponse<ResultDetailArticle>>()

        val client = apiService.getDetailArticle("makanan-gaya-hidup",titleKey)
        client.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({
                    response -> val articleDetail = response.results
                    if(articleDetail != null){
                        resultData.onNext(
                                if(articleDetail != null) ApiResponse.Success(articleDetail)
                                else ApiResponse.Empty
                        )
                    }
                },{
                    error -> resultData.onNext(ApiResponse.Error(error.message.toString()))
                    Log.e("error_RemoteDataSource DetailArticle",error.message.toString())
                })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getListCategory():Flowable<ApiResponse<List<ResultsItemCategory>>>{
        val resultData = PublishSubject.create<ApiResponse<List<ResultsItemCategory>>>()

        val client = apiService.getListCategory()

        client.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({
                    response -> val listCategory = response.results
                    if (listCategory != null) {
                        resultData.onNext(
                                if(listCategory.isNotEmpty()) ApiResponse.Success(listCategory)
                                else ApiResponse.Empty
                        )
                    }
                },{
                    error -> resultData.onNext(ApiResponse.Error(error.message.toString()))
                    Log.e("error_RemoteDataSource ListCategory", error.message.toString())
                })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getContentCategory(tag:String):Flowable<ApiResponse<List<ResultsItemCooking>>>{
        val resultData = PublishSubject.create<ApiResponse<List<ResultsItemCooking>>>()

        val client = apiService.getCategoryContent(tag)
        client.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({
                    response -> val listCategoryContent = response.results
                    if(listCategoryContent != null){
                        resultData.onNext(
                                if(listCategoryContent.isNotEmpty()) ApiResponse.Success(listCategoryContent)
                                else ApiResponse.Empty
                        )
                    }
                },{
                    error -> resultData.onNext(ApiResponse.Error(error.message.toString()))
                    Log.e("error_RemoteDataSource ContentCategory", error.message.toString())
                })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun getSearch(querySearch:String):Flowable<ApiResponse<List<ResultsItemSearch>>>{
        val resultData = PublishSubject.create<ApiResponse<List<ResultsItemSearch>>>()

        val client = apiService.getSearch(querySearch)

        client.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe({
                    response -> val listSearch = response.results
                    if(listSearch != null){
                        resultData.onNext(
                                if(listSearch.isNotEmpty()) ApiResponse.Success(listSearch)
                                else ApiResponse.Empty
                        )
                    }
                },{
                    error -> resultData.onNext(ApiResponse.Error(error.message.toString()))
                    Log.e("error_RemoteDataSource Search", error.message.toString())
                })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

}