package com.demo.service;

import com.demo.model.Product;
import com.demo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;
    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    @Override
    public Product create(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product update(Product product) {
         return productRepo.save(product);
    }



}
