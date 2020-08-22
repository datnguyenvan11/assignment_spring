package com.demo.service;

import com.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    List<Product> getAll();
    Product create (Product product);
    Product update (Product product);

}
