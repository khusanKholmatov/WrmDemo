package com.example.wrmdemo.wareHouse;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "warehouse")
public class WareHouse {

    @Id
    private Long id;
    private String name;
    private BigDecimal storingPrice;

    //I have no idea why storing price is returning null value

    public WareHouse(long warehouseId) {
    }
}
