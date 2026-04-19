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

# Copy jar
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar app.jar"]