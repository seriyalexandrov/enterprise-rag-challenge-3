package org.erc3.exercise.adapter.outgoing.rest.client.session.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SessionTaskStatus(
    @JsonProperty("task_id")
    val taskId: String,
    @JsonProperty("status")
    val status: String,
    @JsonProperty("spec_id")
    val specId: String? = null,
    @JsonProperty("task_text")
    val taskText: String? = null,
    @JsonProperty("score")
    val score: Double? = null,
)
