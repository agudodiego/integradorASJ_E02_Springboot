FROM openjdk:11
WORKDIR /app
COPY target/series-app-springboot-0.0.1-SNAPSHOT.jar app-series.jar
EXPOSE 8080
CMD ["java", "-jar", "app-series.jar"]