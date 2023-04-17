FROM openjdk:16

COPY target/ip_geolocation-0.0.1-SNAPSHOT.jar ip_geolocation.jar

ENTRYPOINT ["java","-jar","/ip_geolocation.jar"]