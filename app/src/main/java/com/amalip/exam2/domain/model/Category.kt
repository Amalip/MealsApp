package com.amalip.exam2.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Created by Amalip on 10/26/2021.
 */

@Parcelize
@Entity
@JsonClass(generateAdapter = true)
class Category(
    @PrimaryKey(autoGenerate = false)
    val idCategory: Int = 0,
    @Json(name = "strCategory") val name: String = "",
    @Json(name = "strCategoryThumb") val urlThumb: String = "",
    @Json(name = "strCategoryDescription") val description: String = "",
) : Parcelable