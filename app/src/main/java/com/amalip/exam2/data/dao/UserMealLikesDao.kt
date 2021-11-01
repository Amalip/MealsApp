package com.amalip.exam2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amalip.exam2.data.dto.UserMealsLikes

/**
 * Created by Amalip on 10/30/2021.
 */

@Dao
interface UserMealLikesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun executeLikes(userMealsLikes: UserMealsLikes): Long

    @Query("SELECT * FROM UserMealsLikes WHERE userId = :userId AND liked = 1")
    fun getLikedMealsByUserId(userId: Int): List<UserMealsLikes>

}