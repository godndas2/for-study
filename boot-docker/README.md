## Windows에 Docker 설치
방법은 몇 가지 있지만 개인적으로 "Docker for windows" 를 사용해봤으니  
다른 방법으로 시도해보았다.  
1. Docker Desktop Install
2. Windows Store -> Ubuntu 18.04 LTS Install
..(생략)

## Springboot Project jar File 생성
1. 현재 프로젝트의 Source 를 참고해서 Class를 생성해준다
2. Terminal -> mvn clean package OR mvn cli 에서 동일하게 실행해준다
3. project에 target 폴더가 생성되고, jar파일을 확인할 수 있다.
4. Terminal -> java -jar target/springboot-docker-0.0.1-SNAPSHOT.jar
5. Web에서 localhost:8080

## Dockerfile 생성
1 . pom.xml에서 추가해준 docker maven plugin(주석 참조)  
2 . 1에서 지정해준 <dockerDirectory> 경로에 맞게 Dockerfile을 생성해준다.  
3 . Dockerfile을 생성하고, 아래의 명령어를 입력해준다  
<i>ps. 더 많고 다양한 명령어를 사용할 수 있다</i>

```
# For Java 8, try this
# FROM openjdk:8-jdk-alpine

# For Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8080

# Refer to Maven build -> finalName
ADD springboot-docker-0.0.1-SNAPSHOT.jar app.jar

ENV JAVA_OPTS=""

ENTRYPOINT ["java","-jar","/app.jar"]
```

4 . Terminal -> Dockerfile이 있는 경로로 이동 후 입력   
> docker build --tag test:0.1 .
  
5 . Step 1 ~ 4까지 진행되고, 마지막 Warning은 무시해주자  
6 . Terminal -> docker images 입력  
7 . 4에서 빌드한 image가 보인다.  
8 . Terminal -> docker run -p 8080:8080 test:0.1 입력  
9 . Web으로 이동해서 localhost:8080 접속하면  테스트 완료