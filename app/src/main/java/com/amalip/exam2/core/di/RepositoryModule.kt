package com.amalip.exam2.core.di

import com.amalip.exam2.core.plataform.AuthManager
import com.amalip.exam2.core.plataform.NetworkHandler
import com.amalip.exam2.data.api.CategoryApi
import com.amalip.exam2.data.api.MealApi
import com.amalip.exam2.data.source.CategoryRepositoryImpl
import com.amalip.exam2.data.source.MealRepositoryImpl
import com.amalip.exam2.data.source.UserRepositoryImpl
import com.amalip.exam2.domain.repository.CategoryRepository
import com.amalip.exam2.domain.repository.MealRepository
import com.amalip.exam2.domain.repository.UserRepository
import com.amalip.exam2.framework.api.ApiProvider
import com.amalip.exam2.framework.db.MealsDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Amalip on 10/20/2021.
 */

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCategoryRepository(
        apiProvider: ApiProvider,
        mealsDb: MealsDb,
        networkHandler: NetworkHandler
    ): CategoryRepository =
        CategoryRepositoryImpl(
            apiProvider.getEndpoint(CategoryApi::class.java),
            mealsDb.categoryDao(),
            networkHandler
        )

    @Provides
    @Singleton
    fun provideMealRepository(
        apiProvider: ApiProvider,
        mealsDb: MealsDb,
        networkHandler: NetworkHandler
    ): MealRepository =
        MealRepositoryImpl(
            apiProvider.getEndpoint(MealApi::class.java),
            mealsDb.mealDao(),
            mealsDb.userMealLikesDao(),
            networkHandler
        )

    @Provides
    @Singleton
    fun provideUserRepository(authManager: AuthManager, mealsDb: MealsDb): UserRepository =
        UserRepositoryImpl(authManager, mealsDb.userDao())

}