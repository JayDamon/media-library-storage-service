FROM maven:3.6.3-jdk-14 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:14-jdk-buster
#ARG JAR_FILE_NAME=@project.artifactId@-@project.version@
COPY --from=build /home/app/target/*.jar /usr/local/lib/plex-backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/usr/local/lib/plex-backend.jar"]
