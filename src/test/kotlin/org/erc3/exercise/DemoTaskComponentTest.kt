package org.erc3.exercise

import org.erc3.exercise.adapter.outgoing.rest.client.demo.DemoApiClient
import org.erc3.exercise.adapter.outgoing.rest.client.session.SessionApiClient
import org.erc3.exercise.adapter.outgoing.rest.client.session.dto.SessionTaskStatus
import org.erc3.exercise.adapter.outgoing.rest.client.tasks.TasksApiClient
import org.erc3.exercise.support.ComponentTest
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@ComponentTest
class DemoTaskComponentTest {

    @Autowired
    private lateinit var demoApiClient: DemoApiClient

    @Autowired
    private lateinit var sessionApiClient: SessionApiClient

    @Autowired
    private lateinit var tasksApiClient: TasksApiClient

    @Test
    fun `should execute demo lifecycle with stub agent`() {
        val benchmark = "demo"
        val sessionName = "demo-3"

        val session = sessionApiClient.startSession(
            benchmark = benchmark,
            name = sessionName,
            architecture = "kotlin-agent",
        )

        val tasks = sessionApiClient.sessionStatus(session.sessionId)?.tasks ?: error("Missing session status")
        assumeTrue(tasks.isNotEmpty()) { "No tasks returned for session ${session.sessionId}" }

        val agent: DemoAgent = StubDemoAgent(demoApiClient)

        tasks.forEach { task ->
            tasksApiClient.start(taskId = task.taskId)
            agent.solve(task)
            val complete = tasksApiClient.complete(task.taskId)
            println("Task ${task.taskId} completed with status=${complete?.status} score=${complete?.eval?.score}")
            complete?.eval?.logs?.let { println(it) }
        }

        sessionApiClient.submitSession(session.sessionId)
    }

    private interface DemoAgent {
        fun solve(task: SessionTaskStatus)
    }

    private class StubDemoAgent(
        private val demoApiClient: DemoApiClient,
    ) : DemoAgent {
        override fun solve(task: SessionTaskStatus) {
            val secret = demoApiClient.getSecret(task.taskId)
            val answer = when (task.specId) {
                "spec1" -> secret
                "spec2" -> secret.reversed()
                "spec3" -> return
                else -> secret
            }
            demoApiClient.answerTask(task.taskId, answer)
        }
    }
}
