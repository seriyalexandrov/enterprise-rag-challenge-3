package org.erc3.exercise.domain.model

data class AgentTask(
    val benchmark: Benchmark,
    val taskId: String,
    val taskName: String,
    val taskDescription: String,
)
