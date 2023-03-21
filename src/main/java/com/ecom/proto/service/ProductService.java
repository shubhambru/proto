package com.ecom.proto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecom.proto.model.ProductDTO;
import com.ecom.proto.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;
    
    public ResponseEntity<?> getProducts(List<String> sizes, String order){
        try {
            List<ProductDTO> products;
            if(order.equals("ASC_PRICE")){
                products = repository.findAll(Sort.by(Sort.Direction.ASC, "price"));
            }
            else if(order.equals("DSC_PRICE")){
                products = repository.findAll(Sort.by(Sort.Direction.DESC, "price"));
            }
            else{
                products = repository.findAll();
            }
            if(sizes.size()==0){
                return new ResponseEntity<List<ProductDTO>>(products,HttpStatus.OK);
            }
            else{
                List<ProductDTO> sizedProducts = new ArrayList<ProductDTO>();
                for(ProductDTO p : products ){
                    for(String size : sizes){
                        if(p.getAvailableSizes().contains(size)){
                            sizedProducts.add(p);
                            break;
                        }
                    }
                }
                return new ResponseEntity<List<ProductDTO>>(sizedProducts,HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Error fetching data",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
