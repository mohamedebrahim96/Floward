package com.floward.core.database.di

import android.app.Application
import androidx.room.Room
import com.floward.core.database.FlowardDatabase
import com.floward.core.database.FlowardDao
import com.floward.core.database.FlowardPostsDao
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

  @Provides
  @Singleton
  fun provideMoshi(): Moshi {
    return Moshi.Builder()
      .addLast(KotlinJsonAdapterFactory())
      .build()
  }

  @Provides
  @Singleton
  fun provideAppDatabase(
    application: Application
  ): FlowardDatabase {
    return Room
      .databaseBuilder(application, FlowardDatabase::class.java, "Floward.db")
      .fallbackToDestructiveMigration()
      .build()
  }

  @Provides
  @Singleton
  fun provideFlowardDao(appDatabase: FlowardDatabase): FlowardDao {
    return appDatabase.flowardDao()
  }

  @Provides
  @Singleton
  fun provideFlowardPostsDao(appDatabase: FlowardDatabase): FlowardPostsDao {
    return appDatabase.flowardPostsDao()
  }

}
