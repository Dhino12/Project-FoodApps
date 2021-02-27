package com.example.foodapplication.core.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cooking (
        val cookingID:String? = null,

        val servings: String? = null,

        val times: String? = null,

        val ingredient: String? = null,

        val thumb: String? = null,

        val author:String? = null,

        val step: String? = null,

        val title: String? = null,

        val dificulty: String? = null,

        val desc: String? = null,

        val tags:String? = null,

        val isFavorite:Boolean? = false
):Parcelable