package com.ecom.proto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.proto.model.AuthDTO;
import com.ecom.proto.service.AuthService;

@RestController
public class AuthController{

    @Autowired
    private AuthService service;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthDTO user){
        return service.login(user);
    }

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody AuthDTO user){
        return service.signup(user);
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllUsers(){
        return service.all();
    }
}