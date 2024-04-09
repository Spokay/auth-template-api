FROM maven:3.8.7-eclipse-temurin-17 as build

WORKDIR /app

COPY . /app

RUN mvn clean package

FROM alpine/java:17-jre as runtime

COPY target/*.jar /app/app.jar

ENV PORT=$PORT

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "-Dserver.port=$PORT", "/app/app.jar"]