FROM maven:3.8.7-eclipse-temurin-17 as build

COPY . /app

WORKDIR /app

RUN mvn clean package

FROM alpine/java:17-jre as runtime

WORKDIR /app

COPY --from=build /app/target/*.jar /app/app.jar

ENV PORT=$PORT

ENV PROFILE=$PROFILE

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=$PROFILE", "-Dserver.port=$PORT", "/app/app.jar"]