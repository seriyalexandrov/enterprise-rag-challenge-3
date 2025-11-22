package org.erc3.exercise.adapter.outgoing.rest.client

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import org.erc3.exercise.adapter.outgoing.rest.client.someapi.SomeApi
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit

@Configuration
@EnableConfigurationProperties
class RetrofitConfig(
    private val objectMapper: ObjectMapper,
) {

    @Bean
    @ConfigurationProperties(prefix = "some-api")
    fun someApiProperties(): RestApiClientProperties = RestApiClientProperties()

    @Bean
    fun someApi(): SomeApi =
        someApiProperties()
            .let { retrofit(it.baseUrl, it.timeout).create(SomeApi::class.java) }

    private fun retrofit(baseUrl: String, timeout: Duration): Retrofit {
        val client = okHttpClient(timeout)
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .build()
    }

    private fun okHttpClient(timeout: Duration): OkHttpClient {
        val timeoutMillis = timeout.toMillis()
        return OkHttpClient.Builder()
            .callTimeout(timeoutMillis, TimeUnit.MILLISECONDS)
            .connectTimeout(timeoutMillis, TimeUnit.MILLISECONDS)
            .readTimeout(timeoutMillis, TimeUnit.MILLISECONDS)
            .writeTimeout(timeoutMillis, TimeUnit.MILLISECONDS)
            .build()
    }
}
