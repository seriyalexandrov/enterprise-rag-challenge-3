package org.erc3.exercise.adapter.outgoing.rest.client.someapi

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.retry.annotation.Retry
import org.erc3.exercise.adapter.outgoing.rest.client.executeHttp
import org.erc3.exercise.domain.model.SomeApiDomain
import org.erc3.exercise.domain.model.DomainRequest
import org.erc3.exercise.domain.port.outgoing.SomeApiPort
import org.erc3.exercise.utils.log
import org.springframework.stereotype.Component

@Component
class SomeApiClient(
    private val api: SomeApi,
    private val mapper: SomeApiMapper,
) : SomeApiPort {

    @Retry(name = RESILIENCE_SOME_API)
    @CircuitBreaker(name = RESILIENCE_SOME_API, fallbackMethod = "fallback")
    override fun callApi(criteria: DomainRequest): List<SomeApiDomain> =
        mapper.toRequest(criteria)
            .let { api.someSearch(it).executeHttp() }
            ?.let { mapper.toOffers(it) }
            ?: emptyList()

    @Suppress("unused")
    private fun fallback(criteria: DomainRequest, e: Exception): List<SomeApiDomain> {
        log.error("Fallback called for api=$RESILIENCE_SOME_API criteria=${criteria}", e)
        return emptyList()
    }

    companion object {
        const val RESILIENCE_SOME_API = "someApi"
    }
}
