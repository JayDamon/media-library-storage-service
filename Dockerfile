FROM openjdk:latest
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","java ${JAVA_OPTS} -jar /app.jar"]