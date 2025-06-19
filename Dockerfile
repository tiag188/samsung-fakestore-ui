FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/samsung-fakestore-ui-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]