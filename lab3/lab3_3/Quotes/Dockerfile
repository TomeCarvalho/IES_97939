FROM openjdk:11-jre-slim
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
#FROM maven:3.8.3-jdk-11
#
#WORKDIR /Quotes
#COPY . .
#RUN mvn clean install
#
#CMD mvn spring-boot:run