FROM openjdk:21-slim as runner
COPY ./build/libs/lite-0.0.1-SNAPSHOT.jar lite.jar
CMD java -XX:+UseContainerSupport -XX:MaxRAMPercentage=80.0 -jar lite.jar