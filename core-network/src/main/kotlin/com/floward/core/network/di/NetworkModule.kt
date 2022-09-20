package com.floward.core.network.di

import com.floward.core.network.interceptor.HttpRequestInterceptor
import com.floward.core.network.service.FlowardClient
import com.floward.core.network.service.FlowardService
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(HttpRequestInterceptor())
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl("https://my-json-server.typicode.com/")
      .addConverterFactory(MoshiConverterFactory.create())
      .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun provideFlowardService(retrofit: Retrofit): FlowardService {
    return retrofit.create(FlowardService::class.java)
  }

  @Provides
  @Singleton
  fun provideFlowardClient(flowardService: FlowardService): FlowardClient {
    return FlowardClient(flowardService)
  }
}
