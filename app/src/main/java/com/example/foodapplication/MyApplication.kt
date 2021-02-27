package com.example.foodapplication

import android.app.Application
import com.example.foodapplication.core.di.CoreComponent
import com.example.foodapplication.core.di.DaggerCoreComponent
import com.example.foodapplication.di.AppComponent
import com.example.foodapplication.di.DaggerAppComponent

open class MyApplication :Application(){

    private val coreComponent:CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent:AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }

}