package com.floward.core.data.repository


import androidx.annotation.WorkerThread
import com.floward.core.database.FlowardPostsDao
import com.floward.core.network.Dispatcher
import com.floward.core.network.FlowardAppDispatchers
import com.floward.core.network.model.FlowardErrorResponse
import com.floward.core.network.service.FlowardClient
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
  private val flowardClient: FlowardClient,
  private val flowardPostsDao: FlowardPostsDao,
  @Dispatcher(FlowardAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : PostsRepository {

  @WorkerThread
  override fun fetchPosts(
    userId: Int,
    onComplete: () -> Unit,
    onError: (String?) -> Unit
  ) = flow {
    val flowardInfo = flowardPostsDao.getflowardInfo(userId)
    if (flowardInfo == null) {
      /**
       * fetches a [flowardInfo] from the network and getting [ApiResponse] asynchronously.
       */
      val response = flowardClient.fetchPosts(userId = userId)
      response.suspendOnSuccess {
        flowardPostsDao.insertflowardInfo(data.asEntity())
        emit(data)
      }
        // handles the case when the API request gets an error response.
        // e.g., internal server error.
        .onError {
          /** maps the [ApiResponse.Failure.Error] to the [FlowardErrorResponse] using the mapper. */
          //map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
        }
        // handles the case when the API request gets an exception response.
        // e.g., network connection error.
        .onException { onError(message) }
    } else {
      emit(flowardInfo)
    }
  }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
