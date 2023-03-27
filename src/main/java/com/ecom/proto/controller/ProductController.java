package com.ecom.proto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.proto.service.ProductService;

@CrossOrigin
@Controller
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    ProductService service;

    @GetMapping
    public ResponseEntity<?> getProducts(@RequestParam(name="sizes" , defaultValue = "") List<String> sizes, @RequestParam(name="order" , defaultValue = "") String order){
        return service.getProducts(sizes , order);
    }
    
}

