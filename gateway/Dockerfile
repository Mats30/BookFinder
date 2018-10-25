FROM maven:3.5.4-jdk-11-slim AS build

WORKDIR /home/gateway
COPY src ./src
COPY pom.xml .
RUN mvn verify

FROM openjdk:11-jre-slim
ENTRYPOINT ["java", "-jar", "/app/gateway.jar"]
WORKDIR /app
COPY --from=build /home/gateway/target .
EXPOSE 8765