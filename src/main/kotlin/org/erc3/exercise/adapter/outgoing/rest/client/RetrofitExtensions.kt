package org.erc3.exercise.adapter.outgoing.rest.client

import org.erc3.exercise.utils.log
import retrofit2.Call
import java.io.IOException

fun <T> Call<T>.executeHttp(): T? =
    try {
        log.info("Request to ${this.request().url} body=${this.request().body}")
        val response = this.execute()
        when {
            response.isSuccessful -> {
                log.info("Response ${this.request().url} body=${response.body()}")
                response.body()
            }
            response.code() in 400..499 -> {
                val error = "Request to ${this.request().url} failed with non-recoverable code=${response.code()} body=${response.errorBody()?.string()}"
                log.error(error)
                throw NonRetryableException(error)
            }
            else -> {
                log.error("Request to ${this.request().url} failed with code=${response.code()} body=${response.errorBody()?.string()}")
                throw RetryableException()
            }
        }
    } catch (e: IOException) {
        log.error("Request to ${this.request().url} failed", e)
        throw RetryableException()
    }
