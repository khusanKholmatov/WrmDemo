package com.example.wrmdemo.wareHouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/wr")
public class WareHouseController {

    @Autowired
    WareHouseRepository wareHouseRepository;

    @GetMapping("/{id}")
    public Mono<WareHouse> getById(@PathVariable Long id) {
        return wareHouseRepository.findById(id);
    }

}
