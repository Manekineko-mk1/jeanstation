FROM openjdk:11.0.10-jdk-slim-buster
EXPOSE 8080
COPY /build/libs/jeanstation-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]