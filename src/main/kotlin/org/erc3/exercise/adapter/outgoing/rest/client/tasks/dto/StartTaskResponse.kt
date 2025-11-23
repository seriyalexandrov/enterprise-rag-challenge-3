package org.erc3.exercise.adapter.outgoing.rest.client.tasks.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class StartTaskResponse(
    @JsonProperty("task_id")
    val taskId: String,
    @JsonProperty("session_id")
    val sessionId: String,
    @JsonProperty("status")
    val status: String,
)
