FROM openjdk:11
ARG JAR_FILE=target/sonaragent.jar
COPY ${JAR_FILE} sonaragent.jar
ENTRYPOINT ["java","-jar","/sonaragent.jar"]