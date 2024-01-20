package com.sber.demo.sberdemoproject.internal.entity.phoneservice.requests;

import lombok.Data;

@Data
public class UpdateItemRequest {
    private Long id;
    private String manufacturer;
    private String name;
    private String description;
    private Long storageSpaceGb;
    private Integer quantity;
    private Integer price;
}
