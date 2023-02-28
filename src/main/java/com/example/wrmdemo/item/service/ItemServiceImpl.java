package com.example.wrmdemo.item.service;

import com.example.wrmdemo.dto.BoxDto;
import com.example.wrmdemo.dto.TruckDto;
import com.example.wrmdemo.item.dto.ItemDto;
import com.example.wrmdemo.item.dto.ItemResponseDTO;
import com.example.wrmdemo.item.entity.Item;
import com.example.wrmdemo.item.mapper.ItemMapper;
import com.example.wrmdemo.item.repository.CustomItemRepository;
import com.example.wrmdemo.item.repository.ItemRepository;
import com.example.wrmdemo.wareHouse.WareHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl {

    private final CustomItemRepository customItemRepository;
    private final WareHouseService wareHouseService;
    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    public Flux<ItemResponseDTO> getAllItems() {

        return customItemRepository.findAllItems();
    }

    public Flux<ItemResponseDTO> saveAllCustom(TruckDto truckDto){
        return customItemRepository.saveAll(toFluxItems(truckDto));
    }

    public Flux<ItemResponseDTO> saveAllR2DBC(TruckDto truckDto){
        return itemRepository.saveAll(toFluxItems(truckDto))
                .map(itemMapper::toItemResponseDTO);
    }

    private Flux<Item> toFluxItems(TruckDto truckDto) {
        List<BoxDto> boxDTOs = truckDto.getBoxDTOs();

        return Flux.fromIterable(boxDTOs)
                .flatMap(boxDto -> Mono.just(boxDto.getItemdto())
                        .publishOn(Schedulers.boundedElastic())
                        .map(itemDto -> {
                            Item item = Item.getInstance(itemDto.getType());
                            item.setName(itemDto.getName());
                            item.setWarehouseId(boxDto.getWareHouseId());
                            item.setQuantity(boxDto.getQuantity());
                            item.setFinalPrice(itemDto.getInitPrice());
                            item.calculateFinalPrice(itemDto, boxDto, truckDto);
                            return item;
                        })
                        .subscribeOn(Schedulers.parallel())
                );
    }


}
