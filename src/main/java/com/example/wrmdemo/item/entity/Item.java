package com.example.wrmdemo.item.entity;

import com.example.wrmdemo.dto.BoxDto;
import com.example.wrmdemo.dto.TruckDto;
import com.example.wrmdemo.item.dto.ItemDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

//@Builder
@Getter
@Setter
@NoArgsConstructor
@Table(name = "items")
public abstract class Item {

    @Id
    private Long id;

    @Column(value = "name")
    private String name;

    @Column(value = "final_price")
    private BigDecimal finalPrice;

    @Column(value = "warehouse_id")
    private Long warehouseId;

    @Column(value = "quantity")
    private int quantity;

    @Column(value = "measure_type")
    private String measureType;

    public static Item getInstance(String itemType) {
        if(itemType.equals("COUNT")) {
            return new CountedItem();
        } else if (itemType.equals("MEASURE")) {
            return new MeasuredItem();
        } return new WeighedItem();

    }

    public abstract void calculateFinalPrice(ItemDto initPrice, BoxDto boxDto, TruckDto truckDto);

}
