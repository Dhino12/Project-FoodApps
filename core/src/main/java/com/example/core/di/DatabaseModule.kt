package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.source.local.room.CookingDao
import com.example.core.data.source.local.room.CookingDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context:Context):CookingDatabase = Room.databaseBuilder(
        context,
        CookingDatabase::class.java,
        "cooking.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideCookingDao(database: CookingDatabase):CookingDao = database.cookingDao()
}