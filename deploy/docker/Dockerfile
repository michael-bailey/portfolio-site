# syntax=docker/dockerfile:1.7

# ---- Build stage ----
FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /app

# Copy just the Gradle wrapper and root config first — these change rarely,
# so this layer stays cached almost always.
COPY gradlew ./
COPY gradle ./gradle
RUN chmod +x gradlew

# Copy the whole project. With the cache mount below, this COPY invalidating
# the layer doesn't matter much — Gradle's own up-to-date checks and the
# dependency/build cache do the real work.
COPY . .

# BuildKit cache mount: persists Gradle dependency + build cache across
# builds without baking it into the image layers.
RUN --mount=type=cache,target=/root/.gradle/caches \
    --mount=type=cache,target=/root/.gradle/wrapper \
    ./gradlew :modules:server:buildFatJar --no-daemon --build-cache

# ---- Runtime stage ----
FROM eclipse-temurin:21-jre-jammy AS runtime

RUN useradd --system --uid 1000 appuser
WORKDIR /app

COPY --from=builder /app/modules/server/build/libs/*-all.jar app.jar

USER appuser
EXPOSE 8080

ENTRYPOINT ["java", \
  "-XX:+UseContainerSupport", \
  "-XX:MaxRAMPercentage=75.0", \
  "-jar", "app.jar"]