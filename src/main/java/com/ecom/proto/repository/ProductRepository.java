package com.ecom.proto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.proto.model.ProductDTO;

@Repository
public interface ProductRepository extends JpaRepository<ProductDTO , String>  {
    
}
