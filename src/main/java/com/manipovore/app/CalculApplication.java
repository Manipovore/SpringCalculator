package com.manipovore.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class CalculApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculApplication.class, args);
	}

	@RequestMapping("/home")
	String index() {
		return "index";
	}
}
