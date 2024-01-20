package com.sber.demo.sberdemoproject.internal.entity.phoneservice.items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneItem {
    private Long id;
    private String manufacturer;
    private String name;
    private String description;
    private Long storageSpaceGb;
    private Integer quantity;
    private Integer price;
}
