#FROM java:latest
FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/productservice-1.0.0.jar productservice.jar
ENTRYPOINT ["java", "-jar", "productservice.jar"]

#EXPOSE 8080