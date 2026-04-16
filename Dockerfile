# ======================
# Stage 1: Build
# ======================
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# ======================
# Stage 2: Runtime
# ======================
FROM mcr.microsoft.com/playwright/java:v1.43.0

WORKDIR /app

# Cài Pandoc
RUN apt-get update && apt-get install -y \
    pandoc \
    && rm -rf /var/lib/apt/lists/*

# Copy jar
COPY --from=build /app/target/*.jar app.jar

# JVM optimize
ENV JAVA_OPTS="-Xms256m -Xmx1024m"

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]