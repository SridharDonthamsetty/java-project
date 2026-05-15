FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/java-*.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
