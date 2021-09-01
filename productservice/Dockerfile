#FROM java:latest
FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/productservice-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

#EXPOSE 8080