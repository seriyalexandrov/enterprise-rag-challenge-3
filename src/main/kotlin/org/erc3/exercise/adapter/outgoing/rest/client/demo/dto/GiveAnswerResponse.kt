package org.erc3.exercise.adapter.outgoing.rest.client.demo.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GiveAnswerResponse(
    @JsonProperty("status")
    val status: String? = null,
)
