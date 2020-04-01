FROM openjdk:latest
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-cp","app:app/lib/*","hello.Application"]