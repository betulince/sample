package com.handle.tasks.schema

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Introspected
@Serdeable
data class Request (
    val parameters: Parameters
)