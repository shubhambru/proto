package com.ecom.proto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom.proto.model.AddressDTO;
import com.ecom.proto.service.AddressService;


@CrossOrigin
@Controller
@RequestMapping("/address")
public class AddressController {
    
    @Autowired
    private AddressService service;

    @GetMapping
    public ResponseEntity<?> getAllAddress(@RequestHeader("token") String token) {
        return service.getAllAddress(token);
    }
    
    @PostMapping
    public ResponseEntity<?> addAddress(@RequestHeader("token") String token, @RequestBody AddressDTO address) {
        return service.addAddress(token, address);
    }

    // @PutMapping("{addressID}")
    // public ResponseEntity<?> updateAddress(@RequestHeader("token") String token, @PathVariable long addressID) {
    //     return service.updateAddress(token);
    // }

    @DeleteMapping("{addressID}")
    public ResponseEntity<?> deleteAddress(@RequestHeader("token") String token, @PathVariable long addressID) {
        return service.deleteAddress(token,addressID);
    }
}
