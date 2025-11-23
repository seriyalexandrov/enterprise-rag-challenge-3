package org.erc3.exercise.adapter.outgoing.rest.client.session

import org.erc3.exercise.adapter.outgoing.rest.client.session.dto.SessionStatusRequest
import org.erc3.exercise.adapter.outgoing.rest.client.session.dto.SessionStatusResponse
import org.erc3.exercise.adapter.outgoing.rest.client.session.dto.StartSessionRequest
import org.erc3.exercise.adapter.outgoing.rest.client.session.dto.StartSessionResponse
import org.erc3.exercise.adapter.outgoing.rest.client.session.dto.SubmitSessionRequest
import org.erc3.exercise.adapter.outgoing.rest.client.session.dto.SubmitSessionResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SessionApi {

    @POST("sessions/start")
    @Headers("Content-Type: application/json")
    fun startSession(@Body request: StartSessionRequest): Call<StartSessionResponse>

    @POST("sessions/status")
    @Headers("Content-Type: application/json")
    fun sessionStatus(@Body request: SessionStatusRequest): Call<SessionStatusResponse>

    @POST("sessions/submit")
    @Headers("Content-Type: application/json")
    fun submitSession(@Body request: SubmitSessionRequest): Call<SubmitSessionResponse>
}
