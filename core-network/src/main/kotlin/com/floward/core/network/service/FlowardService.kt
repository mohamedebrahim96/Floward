package com.floward.core.network.service

import com.floward.core.model.Post
import com.floward.core.network.model.UsersResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FlowardService {

  @GET("SharminSirajudeen/test_resources/users")
  suspend fun fetchUsersList(
    @Query("limit") limit: Int = 20,
    @Query("offset") offset: Int = 0
  ): ApiResponse<UsersResponse>

  @GET("SharminSirajudeen/test_resources/posts")
  suspend fun fetchPosts(@Path("userId") userId: Int): ApiResponse<Post>
}
