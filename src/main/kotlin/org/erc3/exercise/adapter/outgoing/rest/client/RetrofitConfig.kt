package org.erc3.exercise.adapter.outgoing.rest.client

import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import org.erc3.exercise.adapter.outgoing.rest.client.demo.DemoApi
import org.erc3.exercise.adapter.outgoing.rest.client.session.SessionApi
import org.erc3.exercise.adapter.outgoing.rest.client.tasks.TasksApi
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
    @ConfigurationProperties(prefix = "challenge.erc3.core-api")
    fun erc3CoreApiProperties(): RestApiClientProperties = RestApiClientProperties()

    @Bean
    fun sessionApi(): SessionApi =
        erc3CoreApiProperties()
            .let { retrofit(it.baseUrl, it.timeout).create(SessionApi::class.java) }

    @Bean
    fun tasksApi(): TasksApi =
        erc3CoreApiProperties()
            .let { retrofit(it.baseUrl, it.timeout).create(TasksApi::class.java) }

    @Bean
    @ConfigurationProperties(prefix = "challenge.erc3.demo-api")
    fun demoApiProperties(): RestApiClientProperties = RestApiClientProperties()

    @Bean
    fun demoApi(): DemoApi =
        demoApiProperties()
            .let { retrofit(it.baseUrl, it.timeout).create(DemoApi::class.java) }

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
