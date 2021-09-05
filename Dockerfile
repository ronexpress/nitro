FROM maven:3.8.1-jdk-11-slim as builder

WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:go-offline
COPY src src
RUN mvn package -DskipTests

FROM openjdk:11-jre-slim as production
EXPOSE 8080
WORKDIR /app

COPY --from=builder /app/target/TicTacToe-0.0.1-SNAPSHOT.jar /app/TicTacToe.jar
CMD "java" "-jar" "TicTacToe.jar"