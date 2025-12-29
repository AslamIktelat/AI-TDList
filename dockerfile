#FROM openjdk:11
#WORKDIR /app
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
# Stage 1: Build the JAR file
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application from the JAR
FROM openjdk:11
COPY --from=build /target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
