package com.demo.controller;

import com.demo.model.Product;
import com.demo.repository.ProductRepo;
import com.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ProductService productService;
    @GetMapping("/list")
    public String getAllProduct (Model model){
        model.addAttribute("productList", productService.getAll());
        return "listProduct";
    }

    @GetMapping("/addProduct")
    public String showAddProduct(Model model){
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct (@ModelAttribute Product product){
        return Optional.ofNullable(productService.create(product))
                .map(t -> "success")
                .orElse("failed");
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Optional<Product> product = productRepo.findById(id);

        model.addAttribute("product", product);
        return "update";
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/update/{id}")
    public String updateProduct( @RequestBody Product product, Model model) {
        Optional<Product> optionalProduct = productRepo.findById(product.getId());
        if (optionalProduct.isPresent()) {
            Product existProduct = optionalProduct.get();
            existProduct.setName(product.getName());
            existProduct.setPrice(product.getPrice());
            existProduct.setCategoryid(product.getCategoryid());
            productRepo.save(existProduct);
        }
        productService.update(product);
        model.addAttribute("product", productRepo.findAll());
        return "redirect:/list";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        productRepo.delete(product);
        model.addAttribute("product", productRepo.findAll());
        return "listProduct";
    }
}
