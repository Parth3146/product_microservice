package com.example.g3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

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

    @Id
    private String id;

    @Embedded
    private ProductInfo attributes;

    public Product(ProductInfo attributes) {
        this.attributes = attributes;
    }

}