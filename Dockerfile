FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/deal-analyzer-0.0.1-SNAPSHOT.jar deal-analyzer-0.0.1.jar
EXPOSE 8080
CMD ["java","-jar","deal-analyzer-0.0.1.jar"]