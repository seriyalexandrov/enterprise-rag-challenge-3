package org.erc3.exercise.adapter.outgoing.rest.client.tasks

import org.erc3.exercise.adapter.outgoing.rest.client.executeHttp
import org.erc3.exercise.adapter.outgoing.rest.client.tasks.dto.*
import org.springframework.stereotype.Component

@Component
class TasksApiClient(
    private val api: TasksApi,
) {

    fun start(taskId: String, benchmark: String = "", specId: String = ""): StartTaskResponse? =
        api.start(StartTaskRequest(taskId = taskId, benchmark = benchmark, specId = specId)).executeHttp()

    fun complete(taskId: String): CompleteTaskResponse? =
        api.complete(CompleteTaskRequest(taskId = taskId)).executeHttp()

    fun log(request: LogTaskRequest) {
        api.log(request).executeHttp()
    }
}
