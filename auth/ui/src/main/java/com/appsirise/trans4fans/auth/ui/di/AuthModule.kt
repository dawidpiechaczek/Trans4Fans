package com.appsirise.trans4fans.auth.ui.di

import com.appsirise.core.ui.qualifier.Main
import com.appsirise.trans4fans.auth.data.ExampleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal class AuthModule {

/* API */

     @Main
     @Provides
     fun provideExampleApi(@Main retrofit: Retrofit): ExampleApi = retrofit.create()

}