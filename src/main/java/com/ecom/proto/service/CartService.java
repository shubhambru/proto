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

    @Autowired
    AuthService authService;
    public ResponseEntity<?> getAllCartItems(String token){
        try {
            if(authService.validateToken(token)) {
                long uid = authService.getUidFromToken(token);
            }
            else {
                return new ResponseEntity<String>("Invalid Token",HttpStatus.UNAUTHORIZED);

            }
            
            return new ResponseEntity<List<CartItemDTO>>(repository.findAll(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error fetching data",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> addToCartItems(String token, long itemId){
        try {
            if(authService.validateToken(token)) {
                long uid = authService.getUidFromToken(token);
                CartItemDTO cartItem = new CartItemDTO();
                cartItem.setProduct_id(itemId);
                cartItem.setUid(uid);
                repository.save(cartItem);
                return new ResponseEntity<String>("Item Added Successfully",HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<String>("Invalid Token",HttpStatus.UNAUTHORIZED);

            }
            
        } catch (Exception e) {
            return new ResponseEntity<String>("Error fetching data",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
