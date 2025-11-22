package org.erc3.exercise.support

import org.erc3.exercise.adapter.outgoing.rest.client.RetrofitConfig
import org.erc3.exercise.adapter.outgoing.rest.client.demobenchmark.DemoApiClient
import org.erc3.exercise.adapter.outgoing.rest.client.someapi.SomeApiClient
import org.erc3.exercise.adapter.outgoing.rest.client.someapi.SomeApiMapper
import org.erc3.exercise.config.ObjectMapperConfig
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ActiveProfiles

/**
 * Bootstraps a lightweight Spring context that contains only REST client beans and WireMock.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@SpringBootTest(
    classes = [
        ObjectMapperConfig::class,
        RetrofitConfig::class,
        DemoApiClient::class,
        SomeApiClient::class,
        SomeApiMapper::class,
    ],
    webEnvironment = SpringBootTest.WebEnvironment.NONE,
)
@AutoConfigureWireMock(port = 0)
@ComponentScan(
    basePackages = [
        "io.github.resilience4j.springboot3",
        "org.springframework.boot.autoconfigure.aop",
        "org.springframework.aop",
    ]
)
@ActiveProfiles("test")
annotation class RestClientTest
