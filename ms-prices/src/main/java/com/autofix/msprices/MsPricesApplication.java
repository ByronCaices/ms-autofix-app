package com.autofix.msprices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsPricesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsPricesApplication.class, args);
    }

}
