# Stage 1: Build the Spring Boot app using Maven
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy Maven configuration and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Run the Spring Boot app
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/LEATHERGADGETSBACKEND-0.0.1-SNAPSHOT.jar app.jar

# Expose the Spring Boot port (default is 8080)
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
