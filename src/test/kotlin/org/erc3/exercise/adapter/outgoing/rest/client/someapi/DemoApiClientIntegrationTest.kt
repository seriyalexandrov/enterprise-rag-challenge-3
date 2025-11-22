package org.erc3.exercise.adapter.outgoing.rest.client.someapi

import io.kotest.matchers.shouldBe
import org.erc3.exercise.adapter.outgoing.rest.client.demobenchmark.DemoApiClient
import org.erc3.exercise.support.RestClientTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@RestClientTest
class DemoApiClientIntegrationTest {

    @Autowired
    private lateinit var demoApiClient: DemoApiClient

    @Test
    fun `should connect to demo api and return expected result for spec1`() {

        val result = demoApiClient.getSecret("demo/spec1")

        result shouldBe "my_secret_value"
    }
}
