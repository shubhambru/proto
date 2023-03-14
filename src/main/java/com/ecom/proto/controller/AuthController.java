package com.ecom.proto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecom.proto.model.AuthDTO;
import com.ecom.proto.service.AuthService;

@Controller
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