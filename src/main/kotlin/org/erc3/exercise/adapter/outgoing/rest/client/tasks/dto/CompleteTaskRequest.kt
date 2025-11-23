package org.erc3.exercise.adapter.outgoing.rest.client.tasks.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CompleteTaskRequest(
    @JsonProperty("task_id")
    val taskId: String,
)
