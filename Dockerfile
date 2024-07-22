# Giai đoạn build
FROM maven:3.9.8-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Giai đoạn chạy
FROM openjdk:17-jdk-alpine
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-jar","app.jar"]

