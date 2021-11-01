package com.amalip.exam2.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amalip.exam2.data.dao.CategoryDao
import com.amalip.exam2.data.dao.MealDao
import com.amalip.exam2.data.dao.UserDao
import com.amalip.exam2.data.dao.UserMealLikesDao
import com.amalip.exam2.data.dto.UserMealsLikes
import com.amalip.exam2.data.dto.UserWithLikedMeals
import com.amalip.exam2.domain.model.Category
import com.amalip.exam2.domain.model.Meal
import com.amalip.exam2.domain.model.User

/**
 * Created by Amalip on 9/30/2021.
 */

@Database(entities = [Category::class, User::class, Meal::class, UserMealsLikes::class], version = 1)
abstract class MealsDb : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun mealDao(): MealDao

    abstract fun userDao(): UserDao

    abstract fun userMealLikesDao(): UserMealLikesDao

}