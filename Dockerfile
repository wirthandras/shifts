FROM openjdk:8-jdk-alpine

LABEL maintanier="agrippa.wia@gmail.com"

VOLUME /tmp

EXPOSE 9000

ARG JAR_FILE=target/Shifts-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} Shifts.jar

ENTRYPOINT ["java", "-jar","/Shifts.jar"]

