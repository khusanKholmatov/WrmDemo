package com.example.wrmdemo.configuration;

public interface StaticString {

    String GET_ALL_ITEMS = "select i.*, w.name as warehouse_name, w.storing_price as warehouse_storing_price from items i \n" +
                                "join warehouse w on w.id = i.warehouse_id;";

}
