package com.appsirise.core.ui.modules

import android.content.Context
import com.appsirise.core.ui.BuildConfig
import com.appsirise.core.ui.qualifier.Auth
import com.appsirise.core.ui.qualifier.Main
import com.appsirise.core.ui.utils.LocalDateAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.LocalDate

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    /* Retrofit */
    @Provides
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(LocalDate::class.java, LocalDateAdapter().nullSafe())
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    fun provideMoshiConverterFactory(
        moshi: Moshi
    ): MoshiConverterFactory = MoshiConverterFactory.create(moshi)

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    fun provideCache(
        @ApplicationContext context: Context
    ): Cache = Cache(context.cacheDir, CACHE_SIZE_BYTES)

    @Main
    @Provides
    fun provideMainOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    //    stethoInterceptor: StethoInterceptor,
        cache: Cache
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
     //       .addNetworkInterceptor(stethoInterceptor)
            .cache(cache)

        return builder.build()
    }

    @Auth
    @Provides
    fun provideAuthOkHttpClient(
       // stethoInterceptor: StethoInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
      //  .addNetworkInterceptor(stethoInterceptor)
        .addNetworkInterceptor(httpLoggingInterceptor)
        .build()

    @Main
    @Provides
    fun provideMainRetrofit(
        @Main okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_API_URL)
        .client(okHttpClient)
        .addConverterFactory(moshiConverterFactory)
        .build()

    @Auth
    @Provides
    fun provideAuthRetrofit(
        @Auth okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_API_URL)
        .client(okHttpClient)
        .addConverterFactory(moshiConverterFactory)
        .build()

    companion object {
        private const val BASE_API_URL = "https://api.thedogapi.com"
        private const val CACHE_SIZE_BYTES = 1024 * 1024 * 2L
    }
}