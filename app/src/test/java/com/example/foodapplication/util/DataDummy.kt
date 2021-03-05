package com.example.foodapplication.util

import android.util.Log
import com.example.core.data.Resource
import com.example.core.domain.model.Cooking
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

object DataDummy {

    fun setupDataAllCooking(): Flowable<List<Cooking>> {
        val cookingRecipes = ArrayList<Cooking>()
        for (i in 1..10){
            cookingRecipes.add(
                    Cooking(
                            cookingID = "it.cookingID",
                            title = "it.title",
                            thumb = "it.thumb",
                            times = "it.times",
                            servings = "it.portion",
                            dificulty = "it.difficulty",
                            tags = "it.tags"
                    )
            )
        }
        return Flowable.fromArray(cookingRecipes)
    }

    fun dummyAllCooking():Flowable<Resource<List<Cooking>>>{
        val dataCooking = PublishSubject.create<Resource<List<Cooking>>>()

        val dataRecipes = setupDataAllCooking()
        dataRecipes.subscribeOn(Schedulers.io())
                .take(1)
                .subscribe{
                    response ->
                    println(response)
                    if(response != null){
                        dataCooking.onNext(Resource.Success(response))
                    }
                }
        return dataCooking.toFlowable(BackpressureStrategy.BUFFER)
    }
}