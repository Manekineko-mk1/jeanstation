#FROM java:latest
FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/cartservice-1.0.0.jar cartservice.jar
ENTRYPOINT ["java", "-jar", "cartservice.jar"]
