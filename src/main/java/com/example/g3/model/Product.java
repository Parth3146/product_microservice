package com.example.g3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @AllArgsConstructor
    public enum TypeEnum {
        PRODUCT("product");
        private final String value;
    }

    private TypeEnum type;

    @Column(name = "PRODUCT_ID")
    @Id
    private String id;

    @Embedded
    private ProductInfo attributes;

    public Product(ProductInfo attributes) {
        this.attributes = attributes;
    }

}