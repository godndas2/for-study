package com.redis.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
//@EnableCaching
@RequiredArgsConstructor
public class RedisCacheConfig {

    private final RedisProperties redisProperties;
    private RedisServer redisServer;



    @PostConstruct
    public void redisServer() throws IOException {
        redisServer = new RedisServer(redisProperties.getPort());
        redisServer.start();
    }

//    private final JedisConnectionFactory jedisConnectionFactory;
//
//    @Bean
//    public RedisCacheManager redisCacheManager() {
//        return RedisCacheManager.create(jedisConnectionFactory);
//    }


}
