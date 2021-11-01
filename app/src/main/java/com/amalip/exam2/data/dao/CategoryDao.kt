package com.amalip.exam2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.amalip.exam2.domain.model.Category

/**
 * Created by Amalip on 9/30/2021.
 */

@Dao
interface CategoryDao {

    @Query("SELECT * FROM Category")
    fun getCategories(): List<Category>

    @Insert(onConflict = REPLACE)
    fun saveCategories(categories: List<Category>): List<Long>

}