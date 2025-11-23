package org.erc3.exercise.adapter.outgoing.rest.client.demo

import org.erc3.exercise.adapter.outgoing.rest.client.demo.dto.GiveAnswerRequest
import org.erc3.exercise.adapter.outgoing.rest.client.demo.dto.SecretRequest
import org.erc3.exercise.adapter.outgoing.rest.client.executeHttp
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class DemoApiClient(
    private val api: DemoApi,
    @param:Value("\${challenge.erc3.api-key}")
    private val apiKey: String,
) {
    fun getSecret(taskId: String): String =
        api.getSecret(
            sanitizeTaskId(taskId),
            SecretRequest(accountKey = apiKey),
        )
            .executeHttp()
            ?.value
            ?: ""

    fun answerTask(taskId: String, answer: String) {
        api.giveAnswer(
            sanitizeTaskId(taskId),
            GiveAnswerRequest(answer = answer, accountKey = apiKey),
        ).executeHttp()
    }

    private fun sanitizeTaskId(taskId: String): String =
        taskId.trim().trim('/').removePrefix("demo/").trim('/')
}
