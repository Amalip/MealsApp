package com.amalip.exam2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import androidx.room.Transaction
import com.amalip.exam2.data.dto.UserWithLikedMeals
import com.amalip.exam2.domain.model.User

/**
 * Created by Amalip on 10/5/2021.
 */

@Dao
interface UserDao {

    @Transaction
    @Query("SELECT * FROM User WHERE User.name = :username AND User.password = :password")
    fun findUser(username: String, password: String): UserWithLikedMeals?

    @Query("SELECT * FROM User WHERE User.name = :username")
    fun findUserByUsername(username: String): User?

    @Insert(onConflict = ABORT)
    fun registerUser(user: User): Long?

}