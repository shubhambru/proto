package com.ecom.proto.model;

import java.util.List;

import jakarta.persistence.Entity;

public class CartProductItem {
    
    private long item_id;
    private long product_id;
    private int quantity;
    private String title;
    private String description;
    private String src1;
    private String src2;
    private boolean isFreeShipping;
    private double price;
    private String style;
    private List<String> availableSizes;

    public CartProductItem(long item_id,int quantity,ProductDTO product){
        this.item_id = item_id;
        this.quantity = quantity;
        this.product_id = product.getProduct_id();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.src1 = "https://proto-photos.onrender.com/"+product.getSrcID()+"_1.jpg";
        this.src2 = "https://proto-photos.onrender.com/"+product.getSrcID()+"_2.jpg";
        this.isFreeShipping = product.isFreeShipping();
        this.price = product.getPrice();
        this.style = product.getStyle();
        this.price = product.getPrice();
    }

    public long getItem_id() {
        return item_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<String> getAvailableSizes() {
        return availableSizes;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public long getProduct_id() {
        return product_id;
    }

    public String getSrc1() {
        return src1;
    }

    public String getSrc2() {
        return src2;
    }

    public String getStyle() {
        return style;
    }

    public String getTitle() {
        return title;
    }

    public boolean isFreeShipping() {
        return isFreeShipping;
    }
}
