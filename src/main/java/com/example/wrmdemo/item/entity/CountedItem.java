package com.example.wrmdemo.item.entity;

import com.example.wrmdemo.dto.BoxDto;
import com.example.wrmdemo.dto.TruckDto;
import com.example.wrmdemo.item.dto.ItemDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountedItem extends Item{

    {
        this.setMeasureType("Number");
    }

    @Override
    public void calculateFinalPrice(ItemDto initPrice, BoxDto boxDto, TruckDto truckDto) {
//        BigDecimal initialPrice = boxDto.getBoxPrice();
//
//        Double overallSize = Flux.just(truckDto.getBoxDTOs())
//                .flatMapIterable(boxDtos -> boxDtos)
//                .map(box -> box.getBoxSize())
//                .reduce(0D, Double::sum)
//                .block();
//
//        Double boxSize = boxDto.getBoxSize();
//
//        BigDecimal deliveryCostOfBox = (truckDto.getOtherExpanses().multiply(new BigDecimal(boxSize)));
//        BigDecimal deliveryCostOfItem = deliveryCostOfBox;
//
//        this.setFinalPrice(getFinalPrice().add(deliveryCostOfItem).add(initialPrice));
//        this.(new BigDecimal(120000));
    }
}
