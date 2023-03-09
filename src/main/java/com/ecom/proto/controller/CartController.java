package com.ecom.proto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.proto.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController{

    @Autowired
    private CartService service;

    @GetMapping
    public ResponseEntity<?> getAllCartItems(@RequestHeader("token") String token){
        return service.getAllCartItems(token);
    }
    @PostMapping
    public ResponseEntity<?> addToCartItems(@RequestHeader("token") String token, @RequestParam(name = "id") long itemId){
        return service.addToCartItems(token, itemId);
    }
    @DeleteMapping("{productID}")
    public ResponseEntity<?> deleteCartItems(@RequestHeader("token") String token, @PathVariable long productID) {
        return service.deleteCartItems(token, productID);
    }
    @PutMapping("/inc/{productID}")
    public ResponseEntity<?> increaseCartItems(@RequestHeader("token") String token, @PathVariable long productID) {
        return service.updateCartItems(token, productID, 1);
    }
    @PutMapping("/dec/{productID}")
    public ResponseEntity<?> decreaseCartItems(@RequestHeader("token") String token, @PathVariable long productID) {
        return service.updateCartItems(token, productID, -1);
    }

}