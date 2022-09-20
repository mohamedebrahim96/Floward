
package com.floward.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.floward.core.database.entitiy.UserEntity
import com.floward.core.database.entitiy.PostEntity

@Database(
  entities = [UserEntity::class, PostEntity::class],
  version = 2,
  exportSchema = true
)
abstract class FlowardDatabase : RoomDatabase() {

  abstract fun flowardDao(): FlowardDao
  abstract fun flowardPostsDao(): FlowardPostsDao
}
