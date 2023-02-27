package com.ecom.proto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecom.proto.model.CartItemDTO;
import com.ecom.proto.repository.CartRepository;

@Service
public class CartService {
    
    @Autowired
    CartRepository repository;

    public ResponseEntity<?> getAllCartItems(){
        try {
            return new ResponseEntity<List<CartItemDTO>>(repository.findAll(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error fetching data",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
