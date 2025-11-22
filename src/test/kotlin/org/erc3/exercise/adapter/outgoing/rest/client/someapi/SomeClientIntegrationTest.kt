package org.erc3.exercise.adapter.outgoing.rest.client.someapi

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import io.kotest.matchers.collections.shouldBeEmpty
import org.erc3.exercise.domain.model.DomainRequest
import org.erc3.exercise.support.RestClientTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@RestClientTest
class SomeClientIntegrationTest {

    @Autowired
    private lateinit var someApiClient: SomeApiClient

    @Autowired
    private lateinit var wireMockServer: WireMockServer

    @BeforeEach
    fun setup() {
        wireMockServer.resetAll()
    }

    @Test
    fun `should retry configured number of times before calling fallback`() {
        wireMockServer.givenThat(
            post(urlEqualTo(URL))
                .willReturn(aResponse().withStatus(500)),
        )

        val result = someApiClient.callApi(DomainRequest("some"))

        result.shouldBeEmpty()
        verify(3, postRequestedFor(urlEqualTo(URL)))
    }

    companion object {
        private const val URL = "/some-api/some/search"
    }
}
