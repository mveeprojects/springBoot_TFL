FROM openjdk:8-jdk-alpine

MAINTAINER Mark

VOLUME /tmp

ADD target/springBoot_TFL-1.0-SNAPSHOT.jar app.jar

ENV JAVA_OPTS=""

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar