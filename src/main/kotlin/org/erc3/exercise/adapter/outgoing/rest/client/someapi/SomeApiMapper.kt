package org.erc3.exercise.adapter.outgoing.rest.client.someapi

import org.erc3.exercise.adapter.outgoing.rest.client.someapi.dto.SomeApiRequest
import org.erc3.exercise.adapter.outgoing.rest.client.someapi.dto.SomeApiResponse
import org.erc3.exercise.domain.model.SomeApiDomain
import org.erc3.exercise.domain.model.DomainRequest
import org.springframework.stereotype.Component

@Component
class SomeApiMapper {

    fun toRequest(criteria: DomainRequest): SomeApiRequest =
        SomeApiRequest(
            field = criteria.field,
        )

    fun toOffers(response: List<SomeApiResponse>): List<SomeApiDomain> =
        response.map { it.toDomain() }

    private fun SomeApiResponse.toDomain(): SomeApiDomain =
        SomeApiDomain(
            field = field
        )
}
