package org.erc3.exercise.adapter.outgoing.rest.client.session

import org.erc3.exercise.adapter.outgoing.rest.client.executeHttp
import org.erc3.exercise.adapter.outgoing.rest.client.session.dto.*
import org.erc3.exercise.domain.model.AgentTask
import org.erc3.exercise.domain.model.Benchmark
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class SessionApiClient(
    private val api: SessionApi,
    @param:Value("\${challenge.erc3.api-key}")
    private val apiKey: String,
) {

    fun startSession(
        benchmark: String,
        name: String,
        architecture: String,
    ): StartSessionResponse =
        api.startSession(
            StartSessionRequest(
                accountKey = apiKey,
                benchmark = benchmark,
                workspace = "my-workspace",
                name = name,
                architecture = architecture,
            ),
        )
            .executeHttp()
            ?: throw RuntimeException("Failed to start session")

    fun getTasks(sessionId: String): List<AgentTask> =
        api.sessionStatus(SessionStatusRequest(sessionId = sessionId))
            .executeHttp()
            ?.let {
                val benchmark = Benchmark.of(it.benchmark)

                it.tasks.map { task ->
                    AgentTask(
                        benchmark = benchmark,
                        taskId = task.taskId,
                        taskName = task.specId,
                        taskDescription = task.taskText,
                    )
                }
            }
            ?: emptyList()

    fun submitSession(sessionId: String): SubmitSessionResponse? =
        api.submitSession(SubmitSessionRequest(sessionId = sessionId)).executeHttp()
}
