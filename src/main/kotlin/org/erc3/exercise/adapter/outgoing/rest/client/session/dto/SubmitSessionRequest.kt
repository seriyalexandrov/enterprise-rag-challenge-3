package org.erc3.exercise.adapter.outgoing.rest.client.session.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SubmitSessionRequest(
    @JsonProperty("session_id")
    val sessionId: String,
)
