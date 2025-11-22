package org.erc3.exercise.adapter.outgoing.rest.client.demobenchmark

import org.erc3.exercise.adapter.outgoing.rest.client.demobenchmark.dto.GiveAnswerRequest
import org.erc3.exercise.adapter.outgoing.rest.client.demobenchmark.dto.SecretRequest
import org.erc3.exercise.adapter.outgoing.rest.client.executeHttp
import org.springframework.stereotype.Component

@Component
class DemoApiClient(
    private val api: DemoApi,
) {

    fun getSecret(taskId: String): String =
        api.getSecret(SecretRequest())
            .executeHttp()
            ?.value
            ?: ""

    fun answerTask(taskId: String, answer: String): Unit =
        api.giveAnswer(GiveAnswerRequest(answer))
            .executeHttp()
            .let { }

}
