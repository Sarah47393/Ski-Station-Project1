FROM openjdk:8
ADD target/SkiStationProject-0.0.1-SNAPSHOT.jar ski.jar
ENTRYPOINT ["java", "-jar","ski.jar"]
EXPOSE 9090
