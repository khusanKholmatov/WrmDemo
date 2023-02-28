package com.example.wrmdemo.item.controller;

import com.example.wrmdemo.dto.TruckDto;
import com.example.wrmdemo.item.service.ItemServiceImpl;
import com.example.wrmdemo.item.dto.ItemResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/items")
public class ItemsController {

    private final ItemServiceImpl itemService;

    @GetMapping
    public Flux<ItemResponseDTO> getAllItems(){
        return itemService.getAllItems();
    }

    @PostMapping("/custom")
    public Flux<ItemResponseDTO> saveAllCustom(@RequestBody TruckDto truckDto) {
        return itemService.saveAllCustom(truckDto);
    }

    @PostMapping("/r2")
    public Flux<ItemResponseDTO> saveAllR2DBC(@RequestBody TruckDto truckDto) {
        return itemService.saveAllR2DBC(truckDto);
    }

}
