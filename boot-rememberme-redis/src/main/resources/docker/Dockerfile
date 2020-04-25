FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8080

# Refer to Maven build -> finalName
ADD boot-rememberme-redis-0.0.1-SNAPSHOT.jar app.jar

ENV JAVA_OPTS=""

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]