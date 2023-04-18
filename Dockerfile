FROM maven:3.8.3-openjdk-16 as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
ADD . $HOME
RUN mvn package

FROM openjdk:16
COPY --from=build /usr/app/target/ip_geolocation-0.0.1-SNAPSHOT.jar /ip_geolocation.jar
ENTRYPOINT ["java","-jar","/ip_geolocation.jar"]