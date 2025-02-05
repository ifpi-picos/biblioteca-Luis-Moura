# Estágio de construção (Build)
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests

# Estágio de execução (Runtime)
FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY --from=build /app/target/app-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]