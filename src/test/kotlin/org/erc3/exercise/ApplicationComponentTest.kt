package org.erc3.exercise

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import org.erc3.exercise.support.ComponentTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@ComponentTest
class ApplicationComponentTest {

    @Autowired
    private lateinit var wireMockServer: WireMockServer

    @BeforeEach
    fun setup() {
        wireMockServer.resetAll()
    }

    @Test
    fun `should start application and stub api`() {
        stubApiResponse()
    }

    private fun stubApiResponse() {
        wireMockServer.stubFor(
            post(urlEqualTo("/some-api/some/search"))
                .willReturn(
                    okJson(
                        """
                        [
                          {
                            "field": "Some"
                          }
                        ]
                        """.trimIndent(),
                    ),
                ),
        )
    }
}
