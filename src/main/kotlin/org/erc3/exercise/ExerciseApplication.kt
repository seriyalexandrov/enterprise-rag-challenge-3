package org.erc3.exercise

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class ExerciseApplication

fun main(args: Array<String>) {
    runApplication<ExerciseApplication>(*args)
}
