package org.erc3.exercise.adapter.outgoing.rest.client.session.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SessionStatusResponse(
    @JsonProperty("status")
    val status: String,
    @JsonProperty("benchmark")
    val benchmark: String? = null,
    @JsonProperty("failed")
    val failed: Int? = null,
    @JsonProperty("new")
    val newTasks: Int? = null,
    @JsonProperty("running")
    val running: Int? = null,
    @JsonProperty("completed")
    val completed: Int? = null,
    @JsonProperty("tasks")
    val tasks: List<SessionTaskStatus> = emptyList(),
    @JsonProperty("name")
    val name: String? = null,
    @JsonProperty("architecture")
    val architecture: String? = null,
    @JsonProperty("flags")
    val flags: List<String> = emptyList(),
)
