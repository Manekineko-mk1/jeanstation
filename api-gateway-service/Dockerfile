FROM adoptopenjdk/openjdk11:alpine-jre
#EXPOSE 8761
ADD target/api-gateway-service-1.0.0.jar api-gateway-service.jar
ENTRYPOINT ["java","-jar","api-gateway-service.jar"]