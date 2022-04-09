package com.example.g3.service;

import com.example.g3.exception.DuplicateProductException;
import com.example.g3.exception.ProductNotFoundException;
import com.example.g3.model.Product;
import com.example.g3.model.ProductInfo;
import com.example.g3.model.ProductResponse;
import com.example.g3.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    private ProductResponse productResponse;

    public ProductResponse findProducts(){
        productResponse = new ProductResponse(new ArrayList<>(productRepo.findAll()));
        return productResponse;
    }

    public ProductResponse addProduct(ProductInfo product) {
        for (Product p: productRepo.findAll().stream().collect(Collectors.toList())){
            if (p.getAttributes().getName().equals(product.getName()) || p.getAttributes().getPrice().equals(product.getPrice()))
                throw new DuplicateProductException("Given product already exist");
        }
        Product p = new Product(product);
        p.setType(Product.TypeEnum.PRODUCT);
        p.setId(getRandomNumberString());
        productRepo.save(p);
        List<Product> list = new ArrayList<>();
        list.add(p);
        productResponse = new ProductResponse(list);
        return productResponse;
    }

    public ProductResponse findProductById(String id) {
        if (productRepo.findById(id).isEmpty()){
            throw new ProductNotFoundException("Product with given ID does not exist!");
        }
        List<Product> list = new ArrayList<>();
        list.add(productRepo.findById(id).get());
        productResponse= new ProductResponse(list);
        return productResponse;
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
