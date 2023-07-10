# Build a java application image based on openjdk 17 and run it on port 8080
FROM openjdk:17-jdk-alpine
EXPOSE 8080
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]