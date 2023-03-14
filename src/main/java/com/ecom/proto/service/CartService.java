package com.ecom.proto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecom.proto.model.CartItemDTO;
import com.ecom.proto.model.CartProductItem;
import com.ecom.proto.model.ProductDTO;
import com.ecom.proto.repository.CartRepository;
import com.ecom.proto.repository.ProductRepository;

@Service
public class CartService {
    
    @Autowired
    CartRepository repository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AuthService authService;
    
    public ResponseEntity<?> getAllCartItems(String token){
        try {
            if(authService.validateToken(token)) {
                long uid = authService.getUidFromToken(token);
                List<CartItemDTO> cartItems = repository.getCartItems(uid);
                List<CartProductItem> cartProductItems = new ArrayList<CartProductItem>();
                for(CartItemDTO i : cartItems){
                    Optional<ProductDTO> product = productRepository.findById(String.valueOf(i.getProduct_id()));
                    cartProductItems.add(new CartProductItem(i.getItem_id(),i.getQuantity(),product.get()));
                }
                return new ResponseEntity<List<CartProductItem>>(cartProductItems,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<String>("Invalid Token",HttpStatus.UNAUTHORIZED);

            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Error fetching data"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> addToCartItems(String token, long itemId){
        try {
            if(authService.validateToken(token)) {
                long uid = authService.getUidFromToken(token);
                if(repository.checkCartItem(uid,itemId)==0){
                    CartItemDTO cartItem = new CartItemDTO();
                    cartItem.setProduct_id(itemId);
                    cartItem.setUid(uid);
                    repository.save(cartItem);
                    return new ResponseEntity<CartItemDTO>(cartItem,HttpStatus.CREATED);
                }
                else{
                    return new ResponseEntity<String>("Item already in cart",HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return new ResponseEntity<String>("Invalid Token",HttpStatus.UNAUTHORIZED);

            }
            
        } catch (Exception e) {
            return new ResponseEntity<String>("Error fetching data",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteCartItems(String token, long productID) {
      try {
        if(authService.validateToken(token)) {
            long uid = authService.getUidFromToken(token);
            CartItemDTO cartItem = repository.getCartItem(uid,productID);
            if(cartItem != null) {
                repository.delete(cartItem);
                return new ResponseEntity<String>("Item has been deleted",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<String>("Item does not exist",HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity<String>("Invalid Token",HttpStatus.UNAUTHORIZED);

        }
          
      } catch (Exception e) {
          return new ResponseEntity<String>("Error fetching data",HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    public ResponseEntity<?> updateCartItems(String token, long productID, int change) {
      try {
        if(authService.validateToken(token)) {
            long uid = authService.getUidFromToken(token);
            
            CartItemDTO cartItem = repository.getCartItem(uid,productID);
            
            if(cartItem != null) {
              
                cartItem.setQuantity(cartItem.getQuantity() + change);
                
                if(cartItem.getQuantity() == 0) {
                  repository.delete(cartItem);
                  return new ResponseEntity<String>("Item has been deleted",HttpStatus.OK);
                }
                repository.save(cartItem);

                return new ResponseEntity<String>("Item has been updated",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<String>("Item does not exist",HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity<String>("Invalid Token",HttpStatus.UNAUTHORIZED);

        }
          
      } catch (Exception e) {
          return new ResponseEntity<String>("Error fetching data"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}
