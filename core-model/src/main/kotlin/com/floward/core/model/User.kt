package com.floward.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.random.Random

@JsonClass(generateAdapter = true)
data class User(
  @field:Json(name = "albumId") val id: Int,
  @field:Json(name = "userId") val userId: Int,
  @field:Json(name = "name") val name: String,
  @field:Json(name = "url") val url: String,
  @field:Json(name = "thumbnailUrl") val thumbnailUrl: String
)
