FROM gradle:8-jdk17 as builder
COPY . /app
WORKDIR /app
RUN gradle build --no-daemon

FROM openjdk:17
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
