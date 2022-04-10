package com.example.g3.controller;

import com.example.g3.model.ProductInfo;
import com.example.g3.model.ProductResponse;
import com.example.g3.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
@Api(description = "Product API having endpoints which are used to interact with product microservice")
public class ProductController {

    @Autowired
    private ProductService productService;

    private ProductResponse productResponse;

    @PostMapping("/addProduct")
    @ApiOperation("Used to create product and add it to the catalog")
    public ResponseEntity<ProductResponse> addProduct(@ApiParam("Information about the product to be added") @RequestBody @Valid ProductInfo product) {
        productResponse = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @GetMapping("/getAll")
    @ApiOperation("Used to get all the products created")
    public ProductResponse getAllProducts(){
        productResponse = productService.findProducts();
        return productResponse;
    }

    @GetMapping("/{id}")
    @ApiOperation("Used to get a product for specific PRODUCT_ID")
    public ProductResponse findProductById(@ApiParam("A unique Product identifier to search for a product") @PathVariable String id) {
        productResponse = productService.findProductById(id);
        return productResponse;
    }
}