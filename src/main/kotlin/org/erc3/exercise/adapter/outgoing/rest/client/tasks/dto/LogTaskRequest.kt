package org.erc3.exercise.adapter.outgoing.rest.client.tasks.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class LogTaskRequest(
    @JsonProperty("task_id")
    val taskId: String,
    @JsonProperty("model")
    val model: String,
    @JsonProperty("usage")
    val usage: TaskUsage,
    @JsonProperty("duration_sec")
    val durationSec: Double? = null,
)
