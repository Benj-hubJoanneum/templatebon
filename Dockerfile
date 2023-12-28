# Base image with Java runtime
FROM openjdk:18-jdk

# Working directory
WORKDIR /app

# Copy Spring Boot application JAR file into container
COPY build/libs/Lunchbon_Backend-0.0.1-SNAPSHOT.jar .

# Expose port of Spring Boot application
EXPOSE 8080

# Command to run application
ENTRYPOINT ["java", "-jar", "Lunchbon_Backend-0.0.1-SNAPSHOT.jar"]
