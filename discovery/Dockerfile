FROM maven:3.5.4-jdk-11-slim AS build

WORKDIR /home/discovery
COPY src ./src
COPY pom.xml .
RUN mvn verify

FROM openjdk:11-jre-slim
ENTRYPOINT ["java", "-jar", "/app/discovery.jar"]
WORKDIR /app
COPY --from=build /home/discovery/target .
EXPOSE 8761