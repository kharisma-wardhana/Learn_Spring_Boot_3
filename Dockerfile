FROM openjdk:20-ea-19-jdk-slim
WORKDIR /app
COPY . /app
RUN ./mvnw clean package -Dmaven.test.skip=true
RUN mv ./target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT [ "tail", "-f", "/dev/null" ]
#ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]