package com.example.wrmdemo.item.dto;

import com.example.wrmdemo.wareHouse.WareHouseDto;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponseDTO {

    private String name;
    private BigDecimal finalPrice;
    private int quantity;
    private String measureType;
    private WareHouseDto wareHouseDto;

}
