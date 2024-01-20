FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/roche-fizz-buzz-*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]