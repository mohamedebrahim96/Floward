package com.floward.core.data.repository


import androidx.annotation.WorkerThread
import com.floward.core.database.FlowardDao
import com.floward.core.network.Dispatcher
import com.floward.core.network.FlowardAppDispatchers
import com.floward.core.network.service.FlowardClient
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
  private val flowardClient: FlowardClient,
  private val flowardDao: FlowardDao,
  @Dispatcher(FlowardAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : UsersRepository {

  @WorkerThread
  override fun fetchUsersList(
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit
  ) = flow {
    var flowards = flowardDao.getAllUsersList().asDomain()
    if (flowards.isEmpty()) {
      /**
       * fetches a list of [floward] from the network and getting [ApiResponse] asynchronously.
       */
      val response = flowardClient.fetchUsersList()
      response.suspendOnSuccess {
        flowards = data.results
        flowards.forEach { floward -> floward.page = page }
        flowardDao.insertflowardList(flowards.asEntity())
        emit(flowardDao.getAllflowardList(page).asDomain())
      }.onFailure { // handles the all error cases from the API request fails.
        onError(message())
      }
    } else {
      emit(flowardDao.getAllflowardList(page).asDomain())
    }
  }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
