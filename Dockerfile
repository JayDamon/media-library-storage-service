FROM maven:3.6.3-jdk-14 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:14-jdk-buster
COPY --from=build /home/app/target/plex-backend-0.0.1-SNAPSHOT.jar /usr/local/lib/plex-backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/plex-backend.jar"]

#VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar", "/app.jar"]