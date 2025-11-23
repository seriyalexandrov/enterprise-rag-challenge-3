package org.erc3.exercise.adapter.outgoing.rest.client.tasks.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TaskEvaluation(
    @JsonProperty("score")
    val score: Double? = null,
    @JsonProperty("logs")
    val logs: String? = null,
)
