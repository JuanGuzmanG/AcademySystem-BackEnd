#construir el jar
#dividir en capas
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

RUN ls -la /app/target


#construccion de imagen final
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /app/target/AcademySystem-0.0.1-SNAPSHOT.jar /app/AcademySystem.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/AcademySystem.jar"]