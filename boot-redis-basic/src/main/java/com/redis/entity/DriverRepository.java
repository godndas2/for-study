package com.redis.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends CrudRepository<Driver,Long> {
}
