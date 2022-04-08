package com.example.g3.service;

import com.example.g3.exception.ProductNotFoundException;
import com.example.g3.model.Product;
import com.example.g3.model.ProductInfo;
import com.example.g3.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> findProducts(){
        return productRepo.findAll().stream().collect(Collectors.toList());
    }

    public Product addProduct(ProductInfo product) {

        Product p = new Product(product);
        p.setType(Product.TypeEnum.PRODUCT);
        p.setId(getRandomNumberString());
        productRepo.save(p);
        return p;
    }

    public Product findProductById(String id) {
        if (productRepo.findById(id).isEmpty()) throw new ProductNotFoundException("Product with given ID does not exist!");
        return productRepo.findById(id).get();
    }

    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

}
