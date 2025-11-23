package org.erc3.exercise.adapter.outgoing.rest.client.tasks

import org.erc3.exercise.adapter.outgoing.rest.client.tasks.dto.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface TasksApi {

    @POST("tasks/start")
    @Headers("Content-Type: application/json")
    fun start(@Body request: StartTaskRequest): Call<StartTaskResponse>

    @POST("tasks/complete")
    @Headers("Content-Type: application/json")
    fun complete(@Body request: CompleteTaskRequest): Call<CompleteTaskResponse>

    @POST("tasks/log")
    @Headers("Content-Type: application/json")
    fun log(@Body request: LogTaskRequest): Call<Unit>
}
