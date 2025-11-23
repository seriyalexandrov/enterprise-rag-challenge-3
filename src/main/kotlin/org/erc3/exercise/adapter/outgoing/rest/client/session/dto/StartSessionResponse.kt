package org.erc3.exercise.adapter.outgoing.rest.client.session.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class StartSessionResponse(
    @JsonProperty("session_id")
    val sessionId: String,
    @JsonProperty("task_count")
    val taskCount: Int,
)
