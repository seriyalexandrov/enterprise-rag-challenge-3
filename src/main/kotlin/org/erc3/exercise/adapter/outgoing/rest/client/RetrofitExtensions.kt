package org.erc3.exercise.adapter.outgoing.rest.client

import org.erc3.exercise.utils.log
import retrofit2.Call
import java.io.IOException

fun <T> Call<T>.executeHttp(): T? =
    try {
        val response = this.execute()
        when {
            response.isSuccessful -> response.body()
            response.code() in 400..499 -> {
                log.error("Request to ${this.request().url} failed with non-recoverable code=${response.code()} body=${response.errorBody()?.string()}")
                null
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
