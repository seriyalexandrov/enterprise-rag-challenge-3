package org.erc3.exercise.domain.agent

import org.erc3.exercise.domain.model.AgentTask

interface Agent {

    fun submitTask(task: AgentTask)
}