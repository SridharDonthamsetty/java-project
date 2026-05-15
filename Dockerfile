FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/java-*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
