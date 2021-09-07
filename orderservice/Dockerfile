#FROM java:latest
FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/orderservice-1.0.0.jar orderservice.jar
ENTRYPOINT ["java", "-jar", "orderservice.jar"]

#EXPOSE 8080