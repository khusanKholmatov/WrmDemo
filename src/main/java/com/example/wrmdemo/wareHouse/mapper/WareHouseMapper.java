package com.example.wrmdemo.wareHouse.mapper;

import com.example.wrmdemo.wareHouse.WareHouse;
import com.example.wrmdemo.wareHouse.WareHouseDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface WareHouseMapper {

    WareHouseDto wareHouseToWareHouseDTO(WareHouse wareHouse);

}
