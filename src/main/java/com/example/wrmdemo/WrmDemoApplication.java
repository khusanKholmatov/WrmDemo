package com.example.wrmdemo;

import com.example.wrmdemo.dto.BoxDto;
import com.example.wrmdemo.dto.TruckDto;
import com.example.wrmdemo.item.dto.ItemDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class WrmDemoApplication implements CommandLineRunner {

    public static void main(String[] args) throws JsonProcessingException {
        TruckDto truckDto = TruckDtoSampleDataGenerator.generateSampleData();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File("input-files/inputItems.json"), truckDto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SpringApplication.run(WrmDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}

class TruckDtoSampleDataGenerator {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int DEFAULT_LENGTH = 10000;

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(index));
        }

        return sb.toString();
    }


    private static final String[] ITEM_TYPES = {"COUNT", "MEASURE", "WEIGHT"};
    private static final long[] wIds = {1,2,3};

    public static TruckDto generateSampleData() {
        TruckDto truckDto = new TruckDto();
        List<BoxDto> boxDTOs = new ArrayList<>();

        Random random = new Random();

        for (int i = 1; i <= DEFAULT_LENGTH; i++) {
            BoxDto boxDto = new BoxDto();
            List<ItemDto> itemDtos = new ArrayList<>();

            int itemCount = random.nextInt(21) + 20;
            double boxSize = random.nextDouble() * 10 + 5;
            BigDecimal boxPrice = BigDecimal.valueOf(random.nextDouble() * 1000);



            ItemDto itemDto = new ItemDto();
            itemDto.setName(generateRandomString(random.nextInt(21) + 20));
            itemDto.setInitPrice(BigDecimal.valueOf(random.nextDouble() * 100));
            itemDto.setType(ITEM_TYPES[random.nextInt(3)]);

            boxDto.setItemdto(itemDto);
            boxDto.setQuantity(itemCount);
            boxDto.setBoxSize(boxSize);
            boxDto.setBoxPrice(boxPrice);
            boxDto.setWareHouseId(wIds[random.nextInt(3)]);
            boxDTOs.add(boxDto);
        }

        truckDto.setBoxDTOs(boxDTOs);
        truckDto.setOtherExpanses(BigDecimal.valueOf(random.nextDouble() * 100000));

        return truckDto;
    }
}
