package com.example.wrmdemo.item.entity;

import com.example.wrmdemo.dto.BoxDto;
import com.example.wrmdemo.dto.TruckDto;
import com.example.wrmdemo.item.dto.ItemDto;
import com.example.wrmdemo.item.entity.base.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasuredItem extends Item {

    {
        this.setMeasureType("per meter cube");
    }

    @Override
    public void calculateFinalPrice(ItemDto initPrice, BoxDto boxDto, TruckDto truckDto) {

    }
}
