package com.handle.tasks.schema

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@Introspected
data class Parameters(
    private val internalValues: MutableMap<String, Any> = mutableMapOf()
) {
    @JsonAnySetter
    fun put(key: String, value: Any) {
        internalValues[key] = value
    }

    @get:JsonAnyGetter
    val parameterValues: Map<String, Any> get() = internalValues.toMap()

    companion object {
        fun from(map: Map<String, Any>): Parameters {
            val p = Parameters()
            map.forEach { (k, v) -> p.put(k, v) }
            return p
        }
    }
}