package com.example.g3.controller;

import com.example.g3.model.Product;
import com.example.g3.model.ProductInfo;
import com.example.g3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductInfo product) {
        Product p = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @GetMapping("/getAll")
    public List<Product> getAllProducts(){
        return productService.findProducts();
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable String id) {
        return productService.findProductById(id);
    }
}