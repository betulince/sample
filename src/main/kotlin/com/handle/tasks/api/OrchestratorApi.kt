package com.handle.tasks.api

import com.handle.tasks.schema.Request
import com.handle.tasks.schema.Response
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.reactivex.Single
import io.swagger.v3.oas.annotations.Parameter
import org.jetbrains.annotations.NotNull
import javax.validation.Valid

interface OrchestratorApi {
    @Post(value = "/tasks", produces = ["application/json"], consumes = ["application/json"])
    fun submit(
        @NotNull @Parameter(description = "Parameters required to submit a task") @Body body: @Valid Request
    ): Single<Response>

}