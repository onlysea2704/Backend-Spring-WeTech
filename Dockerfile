# ======================
# Stage 1: Build
# ======================
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom để tận dụng cache
COPY pom.xml .

# Cache dependency
RUN mvn -B -e -C -T 1C dependency:resolve

# Copy source
COPY src ./src

# Build
RUN mvn clean package -DskipTests

# ======================
# Stage 2: Runtime
# ======================
FROM mcr.microsoft.com/playwright/java:v1.43.0

WORKDIR /app

# Cài pandoc + tối ưu layer
RUN apt-get update && \
    apt-get install -y --no-install-recommends pandoc && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copy jar
COPY --from=build /app/target/*.jar app.jar

# JVM tuning (production safe)
ENV JAVA_OPTS="-XX:+UseContainerSupport \
-XX:MaxRAMPercentage=75.0 \
-XX:+UseG1GC \
-XX:+ExitOnOutOfMemoryError"

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]