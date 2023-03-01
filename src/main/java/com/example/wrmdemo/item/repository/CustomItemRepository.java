package com.example.wrmdemo.item.repository;

import com.example.wrmdemo.item.dto.ItemResponseDTO;
import com.example.wrmdemo.item.entity.base.Item;
import com.example.wrmdemo.wareHouse.WareHouseDto;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import static com.example.wrmdemo.configuration.StaticString.GET_ALL_ITEMS;

@Repository
@RequiredArgsConstructor
public class CustomItemRepository {

    private final ConnectionFactory connectionFactory;
    private final DatabaseClient databaseClient;

    public Flux<ItemResponseDTO> findAllItems() {
        return databaseClient
                .sql(GET_ALL_ITEMS)
                .map((row, metadata) -> {
                    WareHouseDto wareHouse = new WareHouseDto();
                            wareHouse.setName(row.get("warehouse_name", String.class));

                    return ItemResponseDTO.builder()
                            .finalPrice(row.get("final_price", BigDecimal.class))
                            .name(row.get("name", String.class))
                            .wareHouseDto(wareHouse)
                            .measureType(row.get("measure_type", String.class))
                            .quantity(row.get("quantity", Integer.class))
                            .build();
                })
                .all();
    }

    public Mono<ItemResponseDTO> findItem() {
        return databaseClient
                .sql(GET_ALL_ITEMS)
                .map((row, metadata) -> {
                    WareHouseDto wareHouse = new WareHouseDto();
                    wareHouse.setName(row.get("warehouse_name", String.class));

                    ItemResponseDTO item = new ItemResponseDTO();
                    item.setName(row.get("name", String.class));
                    item.setFinalPrice(row.get("final_price", BigDecimal.class));
                    item.setWareHouseDto(wareHouse);
                    return item;
                })
                .one();
    }


    public Flux<ItemResponseDTO> saveAll(Flux<Item> items) {
        return Flux.usingWhen(
                Mono.from(connectionFactory.create()),
                connection -> {
                    Flux<String> insertStatements = items
                            .map(item -> String.format(
                                    "INSERT INTO items (name, final_price, warehouse_id, quantity, measure_type) VALUES ('%s', %f, %d, %d, '%s')",
                                    item.getName(), null, item.getWarehouseId(), item.getQuantity(), item.getMeasureType()
                            ));
                    //todo final_price should accept BigDecimal, but here it is not accepting it. I don't know why.
                    return insertStatements
                            .collect(Collectors.joining(";"))
                            .flatMap(batch -> Mono.from(connection.createStatement("BEGIN;" + batch + "; COMMIT;").execute()))
                            .thenMany(findAllItems());
                },
                Connection::close
        );
    }
}
