```md
---
name: spring-rest-api
description: Build Spring Boot REST APIs. Use this skill whenever the user asks to create or modify Spring Boot REST APIs.
---
# Spring Boot REST API Skill

## When to Use
Use this skill when creating or extending REST APIs in a Spring Boot application.

## Guidelines

- Use Java 17 and Spring Boot 3.x.
- Follow RESTful naming conventions.
- Create `Entity`, `Repository`, `Service`, `Controller`, and `DTO`.
- Use Java Records for DTOs whenever possible.
- Use constructor injection only.
- Never use field injection.
- Validate requests using Bean Validation.
- Return `ResponseEntity` from controllers.
- Implement global exception handling using `@ControllerAdvice`.
- Use SLF4J for logging.
- Generate JUnit 5 + Mockito tests.
- Write clean, readable, production-ready code.

## Example Prompts

- Create a Product CRUD REST API.
- Build a User Management REST API.
- Add pagination and sorting to the Order API.
- Generate a REST API with validation and unit tests.
```
