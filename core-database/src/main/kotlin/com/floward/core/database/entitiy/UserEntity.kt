package com.floward.core.database.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    val id: Int,
    @PrimaryKey val userId: Int,
    val name: String,
    val url: String,
    val thumbnailUrl: String
)
