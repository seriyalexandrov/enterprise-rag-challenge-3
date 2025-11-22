package org.erc3.exercise.adapter.outgoing.rest.client

import jakarta.validation.constraints.NotBlank
import org.springframework.validation.annotation.Validated
import java.time.Duration

@Validated
data class RestApiClientProperties(
    @get:NotBlank
    var baseUrl: String = "",
    var timeout: Duration = Duration.ofSeconds(3),
)
