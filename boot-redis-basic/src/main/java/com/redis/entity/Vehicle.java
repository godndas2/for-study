package com.redis.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("vehicle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    private long id;

    private String numberPlate;
    private String make;
    private String model;
    private Driver driver;

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", numberPlate='" + numberPlate + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", driver=" + driver +
                '}';
    }
}
