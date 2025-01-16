package com.kwanse.khan0116;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Khan0116Application {

    public static void main(String[] args) {
        SpringApplication.run(Khan0116Application.class, args);
    }

}
