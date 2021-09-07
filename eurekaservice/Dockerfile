FROM adoptopenjdk/openjdk11:alpine-jre
#EXPOSE 8761
ADD target/eurekaservice-1.0.0.jar eurekaservice.jar
ENTRYPOINT ["java","-jar","eurekaservice.jar"]