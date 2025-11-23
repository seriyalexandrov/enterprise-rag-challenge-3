package org.erc3.exercise.adapter.outgoing.rest.client.session

import org.erc3.exercise.adapter.outgoing.rest.client.session.dto.SessionStatusRequest
import org.erc3.exercise.adapter.outgoing.rest.client.session.dto.SessionStatusResponse
import org.erc3.exercise.adapter.outgoing.rest.client.session.dto.StartSessionRequest
import org.erc3.exercise.adapter.outgoing.rest.client.session.dto.StartSessionResponse
import org.erc3.exercise.adapter.outgoing.rest.client.session.dto.SubmitSessionRequest
import org.erc3.exercise.adapter.outgoing.rest.client.session.dto.SubmitSessionResponse
import org.erc3.exercise.adapter.outgoing.rest.client.executeHttp
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

    fun sessionStatus(sessionId: String): SessionStatusResponse? =
        api.sessionStatus(SessionStatusRequest(sessionId = sessionId)).executeHttp()

    fun submitSession(sessionId: String): SubmitSessionResponse? =
        api.submitSession(SubmitSessionRequest(sessionId = sessionId)).executeHttp()
}
