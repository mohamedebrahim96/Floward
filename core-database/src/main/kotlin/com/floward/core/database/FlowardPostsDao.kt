package com.floward.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.floward.core.database.entitiy.PostEntity

@Dao
interface FlowardPostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertflowardInfo(flowardInfo: PostEntity)

    @Query("SELECT * FROM PostEntity WHERE userId = :userId_")
    suspend fun getflowardInfo(userId_: Int): PostEntity?
}
