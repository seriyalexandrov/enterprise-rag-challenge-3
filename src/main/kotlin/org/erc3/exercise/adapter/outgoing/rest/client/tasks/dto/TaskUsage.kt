package org.erc3.exercise.adapter.outgoing.rest.client.tasks.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TaskUsage(
    @JsonProperty("prompt_tokens")
    val promptTokens: Int,
    @JsonProperty("completion_tokens")
    val completionTokens: Int,
    @JsonProperty("total_tokens")
    val totalTokens: Int,
)
