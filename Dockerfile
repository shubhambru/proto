#
# Build stage
#
FROM maven:3.9.0-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:17-jdk-slim
COPY --from=build /target/proto-0.0.1-SNAPSHOT.jar proto.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","proto.jar"]