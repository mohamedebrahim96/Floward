package com.floward.core.data.repository


import androidx.annotation.WorkerThread
import com.floward.core.model.Post
import kotlinx.coroutines.flow.Flow

interface PostsRepository {

    @WorkerThread
    fun fetchPosts(
        userId: Int,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<Post>
}
