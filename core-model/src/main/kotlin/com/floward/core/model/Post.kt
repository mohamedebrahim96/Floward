package com.floward.core.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Post(
  @field:Json(name = "userId") val userId: Int,
  @field:Json(name = "id") val id: Int,
  @field:Json(name = "title") val title: String,
  @field:Json(name = "body") val body: String
) : Parcelable 