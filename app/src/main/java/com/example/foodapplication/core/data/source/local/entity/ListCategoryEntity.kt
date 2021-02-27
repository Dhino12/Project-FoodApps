package com.example.foodapplication.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "listCategory")
@Parcelize
data class ListCategoryEntity(

        val category: String? = null,

        @PrimaryKey
        val key: String,
):Parcelable