# Stage 1: Build the application using Maven Wrapper within a Java environment
FROM openjdk:22-slim AS build

# Install curl to download Maven (in case you want to use Maven directly)
RUN apt-get update && apt-get install -y curl

# Set working directory in the Docker image
WORKDIR /app

# Copy the Maven wrapper and project definition files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Ensure the Maven wrapper is executable
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the project source files
COPY src src

# Build the application without running tests
RUN ./mvnw clean package -DskipTests

# Stage 2: Setup the runtime environment
FROM openjdk:22-slim
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
