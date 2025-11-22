package org.erc3.exercise.adapter.outgoing.rest.client.demobenchmark.dto

import com.fasterxml.jackson.annotation.JsonIgnore

data class SecretRequest(
    @JsonIgnore
    val message: String = ""
)
