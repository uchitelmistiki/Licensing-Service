FROM maven:3.8.4-openjdk-17 AS build  
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

FROM openjdk:17.0.1-jdk-slim 
VOLUME /tmp
COPY --from=build app/target/licensing-service.jar /app-service/licensing-service.jar
WORKDIR /app-service
EXPOSE 8081  
ENTRYPOINT ["java","-jar","/usr/app/licensing-service.jar"]