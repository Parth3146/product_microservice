package com.example.g3.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {

    @ApiModelProperty("Name of the Product")
    @Column(name = "PRODUCT_NAME")
    @NotNull(message = "Product name should not be null!")
    private String name;

    @ApiModelProperty("Description about the product")
    @Column(name = "PRODUCT_DESCRIPTION")
    private String desc;

    @ApiModelProperty("Price of the product")
    @Column(name = "PRODUCT_PRICE")
    @Min(0)
    private Double price;
}
