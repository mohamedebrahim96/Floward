package com.floward.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val flowardAppDispatchers: FlowardAppDispatchers)

enum class FlowardAppDispatchers {
  IO
}
