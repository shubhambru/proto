package com.ecom.proto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecom.proto.model.ProductDTO;
import com.ecom.proto.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;
    
    public ResponseEntity<?> getAllProducts(){
        try {
            return new ResponseEntity<List<ProductDTO>>(repository.findAll(),HttpStatus.OK);            
        } catch (Exception e) {
            return new ResponseEntity<String>("Error fetching data",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
