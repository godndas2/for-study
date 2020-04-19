package com.redis.web;

import com.redis.entity.Driver;
import com.redis.entity.DriverRepository;
import com.redis.entity.Vehicle;
import com.redis.entity.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;
    private final RedisTemplate redisTemplate;

    @GetMapping("/vehicle")
    public Vehicle ret() {
        Driver driver = new Driver(2L, "tester", "Steve");
        Vehicle vehicle = new Vehicle(1L, "1234", "GER", "BMW", driver);
        vehicleRepository.save(vehicle);

        return vehicle;
    }

    @GetMapping("/getAll")
    public Iterable<Vehicle> retAll() {
        return vehicleRepository.findAll();
    }

    @GetMapping("/driver")
    public Driver driver(){
        Driver driver = new Driver(2L, "tester222", "Brian");
        driverRepository.save(driver);

        return driver;
    }

}
