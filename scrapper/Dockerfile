FROM maven:3.5.4-jdk-11-slim AS build

WORKDIR /home/lab

COPY pom.xml .
RUN mvn verify -DskipTests --fail-never

COPY src ./src
RUN mvn verify

FROM openjdk:11-jre-slim

ENTRYPOINT ["java", "-jar", "/app/BookFinder.jar"]

WORKDIR /app
COPY --from=build /home/lab/target .