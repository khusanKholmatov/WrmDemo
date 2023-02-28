package com.example.wrmdemo.item.mapper;

import com.example.wrmdemo.item.dto.ItemResponseDTO;
import com.example.wrmdemo.item.entity.Item;
import com.example.wrmdemo.wareHouse.mapper.WareHouseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    uses = WareHouseMapper.class
)
public interface ItemMapper {

//    @Mapping(source = "warehouseId", target = "wareHouseDto")
    ItemResponseDTO toItemResponseDTO(Item item);


}
