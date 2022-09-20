package com.floward.core.network.model

/**
 * A customized floward error response.
 *
 * @param code A network response code.
 * @param message A network error message.
 */
data class FlowardErrorResponse(
  val code: Int,
  val message: String?
)
