FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-11-jdk -y
COPY . .

RUN ./gradlew bootJar --no-daemon

FROM openjdk:11-jdk-slim

EXPOSE 8080

COPY --from=build /build/libs/gateway-0.0.1-SNAPSHOT.jar gateway-0.0.1-SNAPSHOT-plain.jar

ENTRYPOINT ["java", "-jar", "gateway-0.0.1-SNAPSHOT-plain.jar"]