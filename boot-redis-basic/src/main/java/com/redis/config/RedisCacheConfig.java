package com.redis.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class RedisCacheConfig {

    private final JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public RedisCacheManager redisCacheManager() {
        return RedisCacheManager.create(jedisConnectionFactory);
    }


}
