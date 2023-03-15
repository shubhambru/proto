package com.ecom.proto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<?> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/{order}")
    public ResponseEntity<?> getAllSortProducts(@PathVariable String order ){
        return service.getAllSortProducts(order);
    }
    
}

