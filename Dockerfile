FROM amazoncorretto:17

ARG JAR_FILE=target/rinhadebackend-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8081