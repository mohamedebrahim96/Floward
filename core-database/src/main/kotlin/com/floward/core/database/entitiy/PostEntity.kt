package com.floward.core.database.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostEntity(
    val userId: Int,
    @PrimaryKey val id: Int,
    val title: String,
    val body: String
)
