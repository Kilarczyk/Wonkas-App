package com.kamil.androidtest.di.module

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kamil.androidtest.api.WonkasApi
import com.kamil.androidtest.api.WorkersRepository
import com.kamil.androidtest.util.AppConstants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
@Suppress("unused")
object NetworkModule {
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideWorkerRepository(wonkasApi: WonkasApi): WorkersRepository{
        return WorkersRepository(wonkasApi)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): WonkasApi{
        return retrofit.create(WonkasApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(workerClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .client(workerClient)
            .baseUrl(AppConstants.WORKERS_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            ))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideWorkerClient(authInterceptor: Interceptor): OkHttpClient{
        return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideInterceptor(): Interceptor{
        return  Interceptor { chain ->
            val newUrl = chain.request().url()
                .newBuilder()
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()
 
            chain.proceed(newRequest)
        }
    }
}