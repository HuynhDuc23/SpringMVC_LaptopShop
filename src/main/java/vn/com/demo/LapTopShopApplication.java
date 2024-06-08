package vn.com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import vn.com.demo.controller.UserController;

@SpringBootApplication
public class LapTopShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(LapTopShopApplication.class, args);
	}

}
