package com.handle.tasks.schema

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable
import io.swagger.v3.oas.annotations.media.Schema

@Introspected
@Serdeable
data class Task(
    @Schema(description = "Unique identifier for the task") val taskID: String,
    @Schema(description = "Parameters submitted for the given task") val parameters: Parameters,
)
