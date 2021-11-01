package com.amalip.exam2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amalip.exam2.domain.model.Meal

/**
 * Created by Amalip on 10/30/2021.
 */

@Dao
interface MealDao {

    @Query("SELECT * FROM Meal WHERE idMeal = :id")
    fun getMealById(id: Int): Meal

    @Query("SELECT * FROM Meal WHERE name LIKE :filter")
    fun getMeals(filter: String): List<Meal>

    @Query("SELECT * FROM Meal WHERE category LIKE :filter")
    fun getMealsByCategory(filter: String): List<Meal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMeals(meals: List<Meal>): List<Long>

}