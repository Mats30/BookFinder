FROM openjdk:10.0.2-jdk as build
VOLUME /tmp

COPY pom.xml .
RUN mvn verify -DskipTests --fail-never

COPY src ./src
RUN mvn verify

FROM openjdk:10.0.2-jre
ENTRYPOINT ["java", "-jar", "/target/BookFinder.jar"]
EXPOSE 8080
