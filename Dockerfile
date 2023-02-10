FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
COPY . /app

WORKDIR /app/build/libs
ENTRYPOINT ["java", "-jar", "trialTask-0.0.1-SNAPSHOT.jar"]