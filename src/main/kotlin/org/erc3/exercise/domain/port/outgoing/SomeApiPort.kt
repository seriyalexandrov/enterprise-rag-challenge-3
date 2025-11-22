package org.erc3.exercise.domain.port.outgoing

import org.erc3.exercise.domain.model.SomeApiDomain
import org.erc3.exercise.domain.model.DomainRequest

interface SomeApiPort {

    fun callApi(criteria: DomainRequest): List<SomeApiDomain>
}
