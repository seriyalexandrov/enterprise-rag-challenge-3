package org.erc3.exercise.adapter.outgoing.rest.client.tasks.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class StartTaskRequest(
    @JsonProperty("task_id")
    val taskId: String,
    @JsonProperty("benchmark")
    val benchmark: String,
    @JsonProperty("spec_id")
    val specId: String,
)
