package com.ecom.proto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Address")
public class AddressDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long uid;

    private String name;
    private String type;
    private String address;
    private String contactNumber;
    private String landmark;
    private String city;
    private String state;
    private String country;
    private int pincode;

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public String getLandmark() {
        return landmark;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public String getCountry() {
        return country;
    }
    public int getPincode() {
        return pincode;
    }
    public long getUid() {
        return uid;
    }
    public String getType() {
        return type;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
