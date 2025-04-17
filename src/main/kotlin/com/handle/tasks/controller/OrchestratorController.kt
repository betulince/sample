package com.handle.tasks.controller

import com.handle.tasks.api.OrchestratorApi
import com.handle.tasks.schema.Parameters
import com.handle.tasks.schema.Request
import com.handle.tasks.schema.Response
import com.handle.tasks.schema.Task
import com.offbytwo.ulid.ULID
import io.micronaut.http.annotation.Controller
import io.reactivex.Single
import java.time.Clock

@Controller("/v1")
class OrchestratorController : OrchestratorApi {

    private val clock = Clock.systemDefaultZone()
    private val ulid = ULID()

    override fun submit(body: Request): Single<Response> {
        val createdAt = clock.instant()
        val taskID = ulid.nextULID(createdAt.toEpochMilli())

        val parameters = Parameters.from(body.parameters.parameterValues)
        val task = Task(taskID, parameters)

        return Single.just(Response(task))
    }
}