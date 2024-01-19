package com.sber.demo.sberdemoproject.repository.phoneservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table(name = "phone_item")
public class DBPhoneItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String manufacturer;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Long storageSpaceGb;

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer price;
}
