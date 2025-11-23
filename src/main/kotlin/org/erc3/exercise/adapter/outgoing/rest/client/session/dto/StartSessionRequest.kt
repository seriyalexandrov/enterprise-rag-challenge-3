package org.erc3.exercise.adapter.outgoing.rest.client.session.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class StartSessionRequest(
    @JsonProperty("account_key")
    val accountKey: String,
    @JsonProperty("benchmark")
    val benchmark: String,
    @JsonProperty("workspace")
    val workspace: String,
    @JsonProperty("name")
    val name: String? = null,
    @JsonProperty("architecture")
    val architecture: String? = null,
)
