package com.ecom.proto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cart")
public class CartItemDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long item_id;

    private Long uid;
    private Long product_id;
    private int quantity=1;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

}
