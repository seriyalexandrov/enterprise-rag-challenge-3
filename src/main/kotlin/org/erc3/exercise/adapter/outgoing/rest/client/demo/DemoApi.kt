package org.erc3.exercise.adapter.outgoing.rest.client.demo

import org.erc3.exercise.adapter.outgoing.rest.client.demo.dto.GiveAnswerRequest
import org.erc3.exercise.adapter.outgoing.rest.client.demo.dto.GiveAnswerResponse
import org.erc3.exercise.adapter.outgoing.rest.client.demo.dto.SecretRequest
import org.erc3.exercise.adapter.outgoing.rest.client.demo.dto.SecretResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface DemoApi {

    //     curl -X POST "https://erc.timetoact-group.at/get_key" \
    //    -H "Content-Type: application/json" \
    //    -d '{
    //      "account_key": "key-your-api-key-here"
    //    }'

    //curl -X POST https://erc.timetoact-group.at/$TASK_ID/secret \
    //  -H 'Content-Type: application/json' \
    //  -d '{}'
    @POST("{taskId}/secret")
    @Headers("Content-Type: application/json")
    fun getSecret(@Path("taskId") taskId: String, @Body request: SecretRequest): Call<SecretResponse>

    //curl -X POST https://erc.timetoact-group.at/$TASK_ID/answer \
    //  -H 'Content-Type: application/json' \
    //  -d '{"answer":"sample"}'
    @POST("{taskId}/answer")
    @Headers("Content-Type: application/json")
    fun giveAnswer(@Path("taskId") taskId: String, @Body request: GiveAnswerRequest): Call<GiveAnswerResponse>
}
