FROM maven:3.8.4-openjdk-17 AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:17.0.1-jdk-slim 
VOLUME /tmp
COPY --from=build /usr/src/app/target/licensing-service.jar /usr/app/licensing-service.jar  
EXPOSE 8081  
ENTRYPOINT ["java","-jar","/usr/app/licensing-service.jar","com.optimagrowth.license.LicensingServiceApplication"]