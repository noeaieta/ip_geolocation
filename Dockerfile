#FROM openjdk:16
#COPY target/ip_geolocation-0.0.1-SNAPSHOT.jar /ip_geolocation.jar
#ENTRYPOINT ["java","-jar","/ip_geolocation.jar"]
#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY . .
COPY target/ip_geolocation-0.0.1-SNAPSHOT.jar /ip_geolocation.jar
ENTRYPOINT ["mvn","spring-boot:run"]