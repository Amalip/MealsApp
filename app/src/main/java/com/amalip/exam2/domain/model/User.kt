package com.amalip.exam2.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlin.random.Random

/**
 * Created by Amalip on 10/1/2021.
 */

@Entity
@JsonClass(generateAdapter = true)
class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,
    var img: String = "",
    val name: String = "",
    val password: String = ""
) {

    @Ignore
    var likes = 0

    fun totalLikes() = "You have liked $likes meals"

    fun setRandomImage() {
        img = when (Random.nextInt(0, 3)) {
            0 -> "https://ft-article-images.s3.ap-south-1.amazonaws.com/1529470913cover_a79af0ea40.jpg"
            1 -> "https://i0.wp.com/hipertextual.com/wp-content/uploads/2021/04/elon-musk.jpeg?fit=1200%2C835&ssl=1"
            2 -> "https://pbs.twimg.com/media/EomTCF2XIAEpYzm.jpg"
            else -> "http://pm1.narvii.com/6720/25e80ad037ae1298d4be1d8b0b6a741c8c180347_00.jpg"
        }
    }

}