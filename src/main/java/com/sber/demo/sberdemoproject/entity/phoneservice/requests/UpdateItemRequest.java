package com.sber.demo.sberdemoproject.entity.phoneservice.requests;

import lombok.Data;

@Data
public class UpdateItemRequest {
    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private Integer price;
}
