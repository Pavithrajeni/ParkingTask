# Use Java 24 base image
FROM eclipse-temurin:24-jdk-alpine

# Create and switch to /app inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/*.jar app.jar

# Expose the Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]