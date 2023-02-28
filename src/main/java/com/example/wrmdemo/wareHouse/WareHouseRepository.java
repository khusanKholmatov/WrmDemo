package com.example.wrmdemo.wareHouse;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WareHouseRepository extends ReactiveCrudRepository<WareHouse, Long> {
}
