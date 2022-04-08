package com.example.g3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {

    @NotNull(message = "Product name should not be null!")
    private String name;

    private String desc;

    @Min(0)
    private Double price;
}
