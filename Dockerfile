#  Dockerfile for Java 17 Spring Boot Application
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]