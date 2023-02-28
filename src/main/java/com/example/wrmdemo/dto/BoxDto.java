package com.example.wrmdemo.dto;

import com.example.wrmdemo.item.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoxDto {

    private ItemDto itemdto;
    private int quantity;
    private Double boxSize;
    private BigDecimal boxPrice;
    private Long wareHouseId;

}
