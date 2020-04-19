package com.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("driver")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

    @Id
    private long id;
    private String name;
    private String surname;
}
