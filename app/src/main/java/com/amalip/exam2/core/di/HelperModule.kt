package com.amalip.exam2.core.di

import android.content.Context
import com.amalip.exam2.core.plataform.AuthManager
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
object HelperModule {

    @Provides
    @Singleton
    fun provideAuthManager(@ApplicationContext context: Context) = AuthManager(context)

}