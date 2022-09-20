package com.floward.core.data.repository


import androidx.annotation.WorkerThread
import com.floward.core.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

  @WorkerThread
  fun fetchUsersList(
    page: Int,
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit
  ): Flow<List<User>>
}
