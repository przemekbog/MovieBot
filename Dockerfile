FROM gradle:6.8.3-jre15 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle :shadowJar --no-daemon

FROM openjdk:15
EXPOSE 8080
RUN mkdir /app
RUN mkdir /app/options
COPY --from=build /home/gradle/src/build/libs/*.jar /app/server.jar

ENTRYPOINT ["java", "-jar", "/app/server.jar"]

#ENTRYPOINT ["java", "-jar", "/home/gradle/src/build/libs/MovieBot-1.0-SNAPSHOT-all.jar"]