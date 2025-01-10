# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS builder

# Set the working directory for Maven
WORKDIR /app

# Copy the Maven project files into the builder container
COPY pom.xml .
COPY src ./src

# Build the application with Maven, skipping tests to speed up the process
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim

# Set the working directory inside the runtime container
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/chat-service-*.jar app.jar

# Copy the .env file (if required)
COPY .env /app/.env

# Expose the port your app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]