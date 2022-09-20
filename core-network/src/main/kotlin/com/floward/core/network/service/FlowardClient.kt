package com.floward.core.network.service

import com.floward.core.model.Post
import com.floward.core.network.model.UsersResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class FlowardClient @Inject constructor(
    private val flowardService: FlowardService
) {

    suspend fun fetchUsersList(
    ): ApiResponse<UsersResponse> =
        flowardService.fetchUsersList()

    suspend fun fetchPosts(
      userId: Int
    ): ApiResponse<Post> =
        flowardService.fetchPosts(
          userId = userId
        )
}
