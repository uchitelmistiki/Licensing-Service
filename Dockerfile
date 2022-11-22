FROM maven:3.8.4-openjdk-17 AS build  
COPY . .
RUN mvn clean package

FROM openjdk:17.0.1-jdk-slim 
VOLUME /tmp
COPY --from=build ./target/licensing-service.jar licensing-service.jar
EXPOSE 8081  
ENTRYPOINT ["java","-jar","/usr/app/licensing-service.jar"]