package com.floward.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.floward.core.database.entitiy.UserEntity

@Dao
interface FlowardDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertUsersList(flowardList: List<UserEntity>)


  @Query("SELECT * FROM UserEntity")
  suspend fun getAllUsersList(): List<UserEntity>
}
