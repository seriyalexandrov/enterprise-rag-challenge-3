package org.erc3.exercise.adapter.outgoing.rest.client.demobenchmark

import org.erc3.exercise.adapter.outgoing.rest.client.demobenchmark.dto.GiveAnswerRequest
import org.erc3.exercise.adapter.outgoing.rest.client.demobenchmark.dto.GiveAnswerResponse
import org.erc3.exercise.adapter.outgoing.rest.client.demobenchmark.dto.SecretRequest
import org.erc3.exercise.adapter.outgoing.rest.client.demobenchmark.dto.SecretResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface DemoApi {

    //     curl -X POST "https://erc.timetoact-group.at/get_key" \
    //    -H "Content-Type: application/json" \
    //    -d '{
    //      "account_key": "key-your-api-key-here"
    //    }'

    //curl -X POST https://erc.timetoact-group.at/$TASK_ID/secret \
    //  -H 'Content-Type: application/json' \
    //  -d '{}'
    @POST("secret")
    @Headers("Content-Type: application/json")
    fun getSecret(@Body request: SecretRequest): Call<SecretResponse>

    //curl -X POST https://erc.timetoact-group.at/$TASK_ID/answer \
    //  -H 'Content-Type: application/json' \
    //  -d '{"answer":"sample"}'
    @POST("answer")
    @Headers("Content-Type: application/json")
    fun giveAnswer(@Body request: GiveAnswerRequest): Call<GiveAnswerResponse>
}