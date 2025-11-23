package org.erc3.exercise.adapter.outgoing.rest.client.session.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SubmitSessionResponse(
    @JsonProperty("status")
    val status: String,
)
