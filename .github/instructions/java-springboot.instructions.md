---
description: "Use when working on Java and Spring Boot code in this workspace. Enforces Java 17, constructor injection, JUnit 5 tests, and Lombok for entities and DTOs."
applyTo: ["**/*.java", "pom.xml", "**/pom.xml"]
---

# Java and Spring Boot conventions

- Target Java 17 for this project and keep code compatible with the Java 17 language level.
- Prefer constructor-based dependency injection for Spring components such as controllers, services, repositories, and other beans.
- Use JUnit 5 for unit tests and favor annotations such as @Test, @BeforeEach, and @AfterEach.
- Use Lombok for entities, DTOs, and simple value objects; prefer annotations like @Data, @Getter, @Setter, @Builder, @NoArgsConstructor, and @AllArgsConstructor instead of handwritten boilerplate.
- Keep implementations concise and idiomatic for Spring Boot while following the project’s existing style.
