package org.erc3.exercise.adapter.outgoing.rest.client.session.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SessionTaskStatus(
    @field:JsonProperty("task_id")
    val taskId: String,
    @field:JsonProperty("status")
    val status: String,
    @field:JsonProperty("spec_id")
    val specId: String,
    @field:JsonProperty("task_text")
    val taskText: String,
    @field:JsonProperty("score")
    val score: Double? = null,
)
