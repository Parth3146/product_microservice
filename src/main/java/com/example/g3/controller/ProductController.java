package com.example.g3.controller;

import com.example.g3.model.Product;
import com.example.g3.model.ProductInfo;
import com.example.g3.model.ProductResponse;
import com.example.g3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/addProduct")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody @Valid ProductInfo product) {
        ProductResponse addedProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
    }

    @GetMapping("/getAll")
    public ProductResponse getAllProducts(){
        ProductResponse productResponse = productService.findProducts();
        return productResponse;
    }

    @GetMapping("/{id}")
    public ProductResponse findProductById(@PathVariable String id) {
        ProductResponse productResponseById = productService.findProductById(id);
        return productResponseById;
    }
}