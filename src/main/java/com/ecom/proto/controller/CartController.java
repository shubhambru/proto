package com.ecom.proto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom.proto.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController{

    @Autowired
    private CartService service;

    @GetMapping
    public ResponseEntity<?> getAllCartItems(){
        return service.getAllCartItems();
    }

}