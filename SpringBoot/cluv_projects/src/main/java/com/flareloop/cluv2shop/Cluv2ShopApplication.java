package com.flareloop.cluv2shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Cluv2ShopApplication {
	public static void main(String[] args) {
		SpringApplication.run(Cluv2ShopApplication.class, args);
	}

}