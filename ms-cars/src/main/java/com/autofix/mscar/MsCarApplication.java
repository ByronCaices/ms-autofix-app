package com.autofix.mscar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsCarApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCarApplication.class, args);
	}

}
