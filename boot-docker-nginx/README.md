# Springboot + Docker + Nginx

## Docker Build

1 . pom.xml
```
<properties>
        <docker.image.prefix>docker-springboot</docker.image.prefix>
</properties>

<build>
  <plugins>
       <plugin>
           <groupId>com.spotify</groupId>
           <artifactId>dockerfile-maven-plugin</artifactId>
           <version>1.4.9</version>
           <configuration>
             <repository>${docker.image.prefix}/${project.artifactId}</repository>
           </configuration>
       </plugin>
   <plugins>
<build>
```

2 . Create Dockerfile

> docker build --t test:0.1 .

## Nginx

1 . 직접 image build

> docker image build -f nginx_Dockerfile -t springboot-nginx:0.1 .

2 . docker-compose.yml

> docker-compose up


<i>ps. 프로젝트와 동일하게 구성했다면 localhost:8099/ 로 외부접속 가능

