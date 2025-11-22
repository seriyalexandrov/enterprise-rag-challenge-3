package org.erc3.exercise.adapter.outgoing.rest.client.someapi

import org.erc3.exercise.adapter.outgoing.rest.client.someapi.dto.SomeApiRequest
import org.erc3.exercise.adapter.outgoing.rest.client.someapi.dto.SomeApiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SomeApi {

    @POST("some/search")
    fun someSearch(@Body request: SomeApiRequest): Call<List<SomeApiResponse>>
}
