#FROM java:latest
FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/userservice-1.0.0.jar userservice.jar
ENTRYPOINT ["java", "-jar", "userservice.jar"]

#EXPOSE 8080