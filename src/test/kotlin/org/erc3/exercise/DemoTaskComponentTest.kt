package org.erc3.exercise

import org.erc3.exercise.adapter.outgoing.rest.client.demo.DemoApiClient
import org.erc3.exercise.adapter.outgoing.rest.client.session.SessionApiClient
import org.erc3.exercise.adapter.outgoing.rest.client.tasks.TasksApiClient
import org.erc3.exercise.domain.agent.Agent
import org.erc3.exercise.domain.model.AgentTask
import org.erc3.exercise.support.ComponentTest
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

        val tasks = sessionApiClient.getTasks(session.sessionId)
        val agent: Agent = StubDemoAgent(demoApiClient)

        tasks.forEach { task ->
            tasksApiClient.start(task.taskId)
            agent.submitTask(task)
            val complete = tasksApiClient.complete(task.taskId)
            println("Task ${task.taskId} completed with status=${complete?.status} score=${complete?.eval?.score}")
            complete?.eval?.logs?.let { println(it) }
        }

        sessionApiClient.submitSession(session.sessionId)
    }

    private class StubDemoAgent(
        private val demoApiClient: DemoApiClient,
    ) : Agent {

        override fun submitTask(task: AgentTask) {
            val secret = demoApiClient.getSecret(task.taskId)
            val answer = when (task.taskName) {
                "spec1" -> secret
                "spec2" -> secret.reversed()
                "spec3" -> return
                else -> secret
            }
            demoApiClient.answerTask(task.taskId, answer)
        }
    }
}
