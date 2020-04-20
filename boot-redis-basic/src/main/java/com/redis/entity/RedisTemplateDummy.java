package com.redis.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

@RequiredArgsConstructor
public class RedisTemplateDummy {

    private final RedisTemplate redisTemplate;

    public void runRedisCommandsViaRedisTemplate() {
        // String version
        redisTemplate.opsForValue().set("hello", "world");
        redisTemplate.opsForValue().set("keyA", "valueA");

        String hello = (String) redisTemplate.opsForValue().get("hello");
        String key = (String) redisTemplate.opsForValue().get("keyA");

        System.out.println(hello);
        System.out.println(key);

        // List version
        redisTemplate.opsForList().leftPush("keyListA", "valueListA");
        // Hash version
        redisTemplate.opsForHash().put("hashA", "hashKeyA", "hashValueA");
        // Set version
        redisTemplate.opsForSet().add("setKeyA", "setValue1", "setValue2", "setValue3");
    }
}
