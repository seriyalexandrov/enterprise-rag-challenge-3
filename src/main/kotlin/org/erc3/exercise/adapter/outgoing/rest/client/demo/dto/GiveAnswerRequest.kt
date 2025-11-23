package org.erc3.exercise.adapter.outgoing.rest.client.demo.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GiveAnswerRequest(
    @JsonProperty("tool")
    val tool: String = "/answer",
    @JsonProperty("answer")
    val answer: String,
    @JsonProperty("account_key")
    val accountKey: String,
)
