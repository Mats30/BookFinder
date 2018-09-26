FROM openjdk:10.0.2-jdk as build

RUN MAVEN_VERSION=3.5.0 \
 && cd /usr/share \
 && wget http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz -O - | tar xzf - \
 && mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven \
 && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

WORKDIR /home/lab

COPY pom.xml .
RUN mvn verify -DskipTests --fail-never

COPY src ./src
RUN mvn verify

FROM openjdk:10.0.2-jre

ENTRYPOINT ["java", "-jar", "/app/BookFinder.jar"]

WORKDIR /app
COPY --from=build /home/lab/target .