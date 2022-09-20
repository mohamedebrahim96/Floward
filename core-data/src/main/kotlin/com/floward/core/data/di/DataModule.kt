
package com.floward.core.data.di

import com.floward.core.data.repository.PostsRepositoryImpl
import com.floward.core.data.repository.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsUsersRepository(
        usersRepositoryImpl: UsersRepositoryImpl
    ): UsersRepositoryImpl

    @Binds
    fun bindsPostsRepository(
        postsRepositoryImpl: PostsRepositoryImpl
    ): PostsRepositoryImpl
}
