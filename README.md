# Description

This project is a solution for an [Enterprise RAG Challenge 3: Agentic AI in Action](https://www.timetoact-group.at/events/enterprise-rag-challenge-part-3).
This isn’t just a competition — it’s a crowdsourced research project exploring how agentic AI can solve real-world business challenges.
There is a [sample demo agent available](https://github.com/trustbit/erc3-agents).

## Build and run

To build the project and execute all tests run:
```
./gradlew clean build
```

To start application locally:
```
./gradlew bootRun
```

## Architecture
This section describes architecture decisions and trade-offs taken during development.

### Project Structure
Project follows hexagonal architecture with the structure:
```

exercise
   ├── domain
   │   ├── model
   │   └──  port
   │         └── outgoing
   ├── application
   │   ├── usecase
   │   └── service
   └── adapter
       ├── incoming
       └── outgoing
```

Hexagonal structure is enforced with simple architecture test `HexagonalArchitectureTest` to make sure
that during project evolution it is NOT broken. [Konsist](https://docs.konsist.lemonappdev.com/getting-started) is 
used as an idiomatic Kotlin implementation instead of the more popular [ArchUnit](https://www.archunit.org/).

## AI Agent Design
This implementation uses:
 - Kotlin as a programming language
 - Springboot as framework (mostly for DI)
 - OpenAI gpt-5-mini model for AI agent
 - Structured Output and Schema Guided Reasoning (SGR) to constraint the model to follow a domain reasoning path 
enforced by structured output. 

## Research goals and strategies

This project explores how to implement Schema-Guided Reasoning (SGR) with Structured Outputs in a Kotlin/Spring Boot stack using the OpenAI Java client. 
The main constraint is that complex JSON Schemas with unions 
([check out Python example](https://gist.github.com/abdullin/46caec6cba361b9e8e8a00b2c48ee07c#file-schema-guided-reasoning-py-L201-L208)) 
are not supported out of the box because the Java client lacks a Python-API schema [sanitizer](https://github.com/openai/openai-python/blob/main/src/openai/lib/_pydantic.py),
which simplifies the schema to a form accepted by the OpenAI API.

We will compare several implementation strategies to understand which one yields the most reliable 
and maintainable agent behavior under these limitations.

### Option 1: Manually Maintained (“Hardcoded”) Simplified Schema

In this option, we generate the full JSON Schema from Kotlin models but MANUALLY simplify it into a form accepted 
by OpenAI (e.g., flattening or rewriting unions). 
The sanitized schema is then hardcoded or embedded into prompts and updated whenever the tool set or models change. 
This trades off automation and convenience for predictable, fully controlled schemas at the cost of ongoing manual maintenance.

### Option 2: Generic Tool Wrapper with Simple Schema

Here we use a single generic schema for all tools, for example, a 
`toolName: String,
toolArguments: Map<String, String>`
The model only needs to produce a simple structured object, and the server is responsible for validating and mapping arguments 
to concrete tool types. This simplifies compatibility with the Java Open API client but weakens type safety on the LLM 
side and increases the burden on server-side validation logic.

### Option 3: Two-Phase Tool Invocation

In this approach, each agent step is split into two LLM calls: 
 - first, the model plans and chooses which tool to call; 
 - second, given the chosen tool, it fills arguments using a dedicated, simple schema for that tool only. 

This avoids unions in a single schema while still giving the model precise structure for arguments. 
The trade-off is higher latency and cost per step in exchange for potentially better correctness and stability of tool calls.

## Testing strategy

TBD