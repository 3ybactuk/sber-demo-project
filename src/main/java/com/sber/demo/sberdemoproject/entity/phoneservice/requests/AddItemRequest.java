package com.sber.demo.sberdemoproject.entity.phoneservice.requests;

import lombok.Data;

@Data
public class AddItemRequest {
    private String manufacturer;
    private String name;
    private String description;
    private Long storageSpaceGb;
    private Integer quantity;
    private Integer price;
}
