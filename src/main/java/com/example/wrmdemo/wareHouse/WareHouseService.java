package com.example.wrmdemo.wareHouse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WareHouseService {

    private final WareHouseRepository wareHouseRepository;

    public Mono<WareHouse> getById(Long wareHouseId) {
        return wareHouseRepository.findById(wareHouseId);
    }
}
