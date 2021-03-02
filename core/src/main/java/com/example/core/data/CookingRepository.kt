package com.example.core.data

import android.annotation.SuppressLint
import android.util.Log
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.*
import com.example.core.domain.model.Article
import com.example.core.domain.model.Category
import com.example.core.domain.model.Cooking
import com.example.core.domain.model.Search
import com.example.core.domain.repository.IFoodRepository
import com.example.core.util.AppExecutors
import com.example.core.util.DataMapper
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CookingRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private  val localDataSource:LocalDataSource,
    private val appExecutors: AppExecutors
): IFoodRepository {

    override fun getAllCooking():Flowable<Resource<List<Cooking>>> =
        object : NetworkBoundResource<List<Cooking>, List<ResultsItemCooking>>(){
            override fun loadFromDB(): Flowable<List<Cooking>> {
                return localDataSource.getAllCooking().map { DataMapper.mapEntitiesCookingToDomain(it) }
            }

            override fun shouldFetch(data: List<Cooking>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): Flowable<ApiResponse<List<ResultsItemCooking>>> {
                return remoteDataSource.getAllCooking()
            }

            override fun saveCallResult(data: List<ResultsItemCooking>) {
                val cookList = DataMapper.mapResponseToEntityCooking(data)
                localDataSource.insertCooking(cookList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getAllArticle():Flowable<Resource<List<Article>>> =
        object : NetworkBoundResource<List<Article>, List<ResultItemArticle>>(){
            override fun loadFromDB(): Flowable<List<Article>> =
                localDataSource.getAllArticle().map { DataMapper.mapEntitiesArticleToDomain(it) }

            override fun shouldFetch(data: List<Article>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flowable<ApiResponse<List<ResultItemArticle>>> =
                remoteDataSource.getAllArticle()

            override fun saveCallResult(data: List<ResultItemArticle>) {
                val articleList = DataMapper.mapResponseToEntityArticle(data)
                localDataSource.insertArticle(articleList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getDetailCooking(key:List<String>):Flowable<Resource<List<Cooking>>> =
        object : NetworkBoundResource<List<Cooking>, List<ResultsDetailCooking>>(){
            override fun loadFromDB(): Flowable<List<Cooking>> {
                Log.e("errorLoadFromDB",key[0])
                Log.e("errorLoadFromDB",localDataSource.getDetailCooking(key[0]).isEmpty.toString())
                val local = localDataSource.getDetailCooking(key[0])

                return local.map { DataMapper.mapEntitiesCookingDetailToDomain(it) }
            }

            override fun shouldFetch(data: List<Cooking>?): Boolean {
                Log.e("errorShouldRepo",data?.isNullOrEmpty().toString())
                return data.isNullOrEmpty()
            }

            override fun createCall(): Flowable<ApiResponse<List<ResultsDetailCooking>>> {
                Log.e("erroCreateCall","error")
                return remoteDataSource.getDetailCooking(key[1])
            }

            override fun saveCallResult(data: List<ResultsDetailCooking>) {

                val detailCooking = DataMapper.mapResponseToEntityCookingDetail(data[0])
                localDataSource.insertDetailCooking(detailCooking)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getDetailArticle(key:List<String>):Flowable<Resource<Article>> =
        object : NetworkBoundResource<Article, ResultDetailArticle>(){
            override fun loadFromDB(): Flowable<Article> =
                localDataSource.getDetailArticle(key[0]).map { DataMapper.mapEntityArticleDetailToDomain(it) }

            override fun shouldFetch(data: Article?): Boolean =
                data?.title == null

            override fun createCall(): Flowable<ApiResponse<ResultDetailArticle>> =
                remoteDataSource.getDetailArticle(key[1])

            override fun saveCallResult(data: ResultDetailArticle) {
                val detailArticle = DataMapper.mapResponseToEntityArticleDetail(data)
                localDataSource.insertDetailArticle(detailArticle)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getListCategory():Flowable<Resource<List<Category>>> =
        object : NetworkBoundResource<List<Category>, List<ResultsItemCategory>>(){
            override fun loadFromDB(): Flowable<List<Category>> =
                localDataSource.getAllListCategory().map { DataMapper.mapEntityListCategoryToDomain(it) }

            override fun shouldFetch(data: List<Category>?): Boolean =
                data.isNullOrEmpty()

            override fun createCall(): Flowable<ApiResponse<List<ResultsItemCategory>>> =
                remoteDataSource.getListCategory()

            override fun saveCallResult(data: List<ResultsItemCategory>) {
                val listCategory = DataMapper.mapResponseToEntityListCategory(data)
                localDataSource.insertListCategory(listCategory)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getAllContentCategory(tag:String):Flowable<Resource<List<Cooking>>> =
        object : NetworkBoundResource<List<Cooking>, List<ResultsItemCooking>>(){
            override fun loadFromDB(): Flowable<List<Cooking>> =
                localDataSource.getAllContentCategory(tag).map { DataMapper.mapEntitiesContentCategoryToDomain(it) }

            override fun shouldFetch(data: List<Cooking>?): Boolean {
                var on = true
                data?.forEach {
                    on = it.tags != tag && data.isNullOrEmpty()
                }
                Log.e("error shouldFetch2",on.toString())
                return on
            }

            override fun createCall(): Flowable<ApiResponse<List<ResultsItemCooking>>> {
                return remoteDataSource.getContentCategory(tag)
            }

            override fun saveCallResult(data: List<ResultsItemCooking>) {
                val cookList = DataMapper.mapResponseContentToEntityCooking(data, tag)
                localDataSource.insertAllContentCategory(cookList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread() )
                    .subscribe()
            }
        }.asFlowable()

    override fun getFavoriteFood():Flowable<List<Cooking>> =
        localDataSource.getFavoriteFood().map { DataMapper.mapEntitiesCookingFavoriteDetailToDomain(it) }

    override fun setFavoriteFood(food:Cooking, state:Boolean){
        val foodEntity = DataMapper.mapDomainToEntityCooking(food)
        appExecutors.diskIO().execute { localDataSource.setFavoriteFood(foodEntity, state) }
    }

    @SuppressLint("CheckResult")
    override fun getSearchFood(query:String?):Flowable<Resource<List<Search>>>{
        val dataSearch = PublishSubject.create<Resource<List<Search>>>()
        Log.e("errorResponse SearchRESPONSE", "error SearchRepo ${query}")
        if( query != null){
            remoteDataSource.getSearch(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .subscribe { response ->
                    when (response) {
                        is ApiResponse.Success -> {
                            dataSearch.onNext(Resource.Success(DataMapper.mapResponseSearchToEntity(response.data)))
                            Log.e("errorResponse SearchSuccess", response.data.isNullOrEmpty().toString())
                        }
                        is ApiResponse.Error -> {
                            dataSearch.onNext(Resource.Error(response.errorMessage))
                            Log.e("errorResponse SearchError", response.toString())
                        }
                        else -> {
                            Log.e("errorResponse SearchElse", response.toString())
                        }
                    }
                    Log.e("errorResponse SearchRESPONSE", response.toString())
                }
        }

        return dataSearch.toFlowable(BackpressureStrategy.BUFFER)
    }
}