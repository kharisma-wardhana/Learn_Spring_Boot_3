FROM openjdk:20-ea-19-jdk-slim as builder
WORKDIR /app
COPY ./.mvn /app/.mvn
COPY ./mvnw /app
COPY ./pom.xml /app
RUN ./mvnw clean
COPY ./src /app/src
COPY ./.env /app/.env
RUN export $(grep -v '^#' ./.env | xargs)
RUN ./mvnw package

FROM openjdk:20-ea-19-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]