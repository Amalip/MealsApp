package com.amalip.exam2.core.di

import android.content.Context
import androidx.room.Room
import com.amalip.exam2.framework.db.MealsDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Amalip on 10/20/2021.
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMealsDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MealsDb::class.java, "meals").addMigrations().build()

}