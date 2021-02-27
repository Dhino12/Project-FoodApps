package com.example.foodapplication.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodapplication.core.data.source.local.entity.*

@Database(
        entities = [
            CookingEntity::class,
            ArticleEntity::class,
            CookingDetailEntity::class,
            ArticleDetailEntity::class,
            ListCategoryEntity::class
        ],
        version = 1,exportSchema = false
)
abstract class CookingDatabase:RoomDatabase() {

    abstract fun cookingDao():CookingDao

    companion object{
        @Volatile
        private var INSTANCE:CookingDatabase? = null

        fun getInstance(context:Context):CookingDatabase =
                INSTANCE ?: synchronized(this){
                    val instance = Room.databaseBuilder(
                            context.applicationContext,
                            CookingDatabase::class.java,
                            "cooking.db"
                    ).fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                    instance
                }
    }
}