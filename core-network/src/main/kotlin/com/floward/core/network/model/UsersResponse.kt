
package com.floward.core.network.model

import com.floward.core.model.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersResponse(
  @field:Json(name = "count") val count: Int,
  @field:Json(name = "next") val next: String?,
  @field:Json(name = "previous") val previous: String?,
  @field:Json(name = "results") val results: List<User>
)
