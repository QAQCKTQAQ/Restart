FROM harbor-dev.fhzny.com:60002/public/openjdk:8-jdk-alpine-common

WORKDIR /app
COPY target/*.jar /app/app.jar

ENV JAVA_OPTS="" JAVA_HEAP_OPTS=""
CMD exec java $JAVA_HEAP_OPTS $JAVA_OPTS -Dspring.profiles.active=default,prod -jar app.jar
