package org.erc3.exercise.domain.model

enum class Benchmark(val value: String) {
    DEMO("demo"),
    STORE("store"),
    ERC3_DEV("erc3-dev"),
    ERC3("erc3");

    companion object {
        fun of(value: String): Benchmark = Benchmark.entries.first { it.value == value }
    }
}