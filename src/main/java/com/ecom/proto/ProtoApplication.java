package com.ecom.proto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ProtoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProtoApplication.class, args);
	}

	@GetMapping("demo")
	public String demo(){
		return "This is a test";
	}

}
