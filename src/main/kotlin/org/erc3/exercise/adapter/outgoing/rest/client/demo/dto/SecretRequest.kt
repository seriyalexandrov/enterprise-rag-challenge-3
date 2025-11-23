package org.erc3.exercise.adapter.outgoing.rest.client.demo.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SecretRequest(
    @JsonProperty("tool")
    val tool: String = "/secret",
    @JsonProperty("account_key")
    val accountKey: String,
)
