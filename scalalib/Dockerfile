FROM gradle:8-jdk17 as build
COPY . /app
WORKDIR /app
RUN gradle shadowJar

FROM openjdk:17
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "app.jar"]
