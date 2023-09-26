FROM amazoncorretto:17

ARG JAR_FILE=target/rinhadebackend-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-XX:+UseParallelGC", "-XX:MaxRAMPercentage=75", "-jar", "app.jar"]

EXPOSE ${SERVER_PORT}