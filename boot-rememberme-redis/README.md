# SpringBoot Security Remeber-Me (Redis)

## Sign in
- Spring Security
- Token (Jwt Version은 따로 만들 예정)
- Remember Me
- Spring Redis

## Redis
- 방화벽에서 6379 port를 열어준다
- 현재 windows 환경이므로, WSL + Docker Desktop 을 사용해서 redis를 install 하고 redis-cli로 미리 redis server를 running 해놓는다.
- running 해놓지 않으면 springboot에서 server를 run할 때, connection Error 
- 프로젝트 server를 띄운 후 signin을 한다
- redis-cli 에서 data를 확인해본다  

![rediscli](https://user-images.githubusercontent.com/34512538/80121149-e75ef400-85c6-11ea-951b-6f022ada4246.PNG)



## How to run?
- Docker  
