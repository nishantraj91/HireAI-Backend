# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

# Yeh line Render ke variables ko jar chalte waqt inject karegi
ENTRYPOINT ["sh", "-c", "java -Dspring.datasource.url=jdbc:mysql://${DB_HOST}:${DB_PORT}/defaultdb?sslMode=REQUIRED&allowPublicKeyRetrieval=true -Dspring.datasource.username=${DB_USER} -Dspring.datasource.password=${DB_PASSWORD} -jar app.jar"]