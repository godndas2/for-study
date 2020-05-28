FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8080

ADD target/boot-docker-nginx-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
