package com.handle.tasks

import io.micronaut.context.ApplicationContext
import io.micronaut.json.JsonMapper
import com.handle.tasks.controller.OrchestratorController
import com.handle.tasks.schema.Parameters
import com.handle.tasks.schema.Request
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class OrchestratorControllerTest {

    private lateinit var controller: OrchestratorController

    companion object {
        private lateinit var context: ApplicationContext
        private lateinit var jsonMapper: JsonMapper

        @BeforeAll
        @JvmStatic
        fun setup() {
            context = ApplicationContext.run()
            jsonMapper = context.getBean(JsonMapper::class.java)
        }

        @AfterAll
        @JvmStatic
        fun teardown() {
            context.close()
        }
    }

    @BeforeEach
    fun setUp() {
        controller = OrchestratorController()
    }

    @Test
    fun submitThrowsSerializationErrorDueToGenericAny() {
        val params = mapOf(
            "source" to mapOf(
                "name" to "source.json",
                "meta" to mapOf(
                    "schemaName" to "File",
                    "schemaVersion" to "1.0"
                )
            ),
            "destination" to mapOf(
                "name" to "destination.json",
                "meta" to mapOf(
                    "schemaName" to "File",
                    "schemaVersion" to "1.0"
                )
            )
        )

        val parameters = Parameters.from(params)
        val request = Request(parameters)

        jsonMapper.writeValueAsBytes(request)
    }
}
