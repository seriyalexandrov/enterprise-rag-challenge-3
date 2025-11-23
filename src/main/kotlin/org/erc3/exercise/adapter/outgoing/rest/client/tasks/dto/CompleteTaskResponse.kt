package org.erc3.exercise.adapter.outgoing.rest.client.tasks.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CompleteTaskResponse(
    @JsonProperty("status")
    val status: String,
    @JsonProperty("eval")
    val eval: TaskEvaluation? = null,
)
