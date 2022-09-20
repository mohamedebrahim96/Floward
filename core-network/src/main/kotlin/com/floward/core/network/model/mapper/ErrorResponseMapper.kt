package com.floward.core.network.model.mapper

import com.floward.core.network.model.FlowardErrorResponse
import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message

/**
 * A mapper for mapping [ApiResponse.Failure.Error] response as custom [FlowardErrorResponse] instance.
 *
 */
object ErrorResponseMapper : ApiErrorModelMapper<FlowardErrorResponse> {

  /**
   * maps the [ApiResponse.Failure.Error] to the [FlowardErrorResponse] using the mapper.
   *
   * @param apiErrorResponse The [ApiResponse.Failure.Error] error response from the network request.
   * @return A customized [FlowardErrorResponse] error response.
   */
  override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): FlowardErrorResponse {
    return FlowardErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
  }
}
