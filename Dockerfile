#  Dockerfile for Java 17 Spring Boot Application
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy dependencies and build output
COPY app/build/libs/*.jar app.jar

# Expose port 8080 (default port for Spring Boot)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]