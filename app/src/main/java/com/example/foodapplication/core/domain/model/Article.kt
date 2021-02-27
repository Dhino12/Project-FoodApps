package com.example.foodapplication.core.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article (
        val key:String?= null,

        val title:String? = null,

        val thumb:String? = null,

        val tags:String? = null,

        val datePublished: String? = null,

        val author: String? = null,

        val description: String? = null,

        val isFavorite:Boolean? = false
):Parcelable