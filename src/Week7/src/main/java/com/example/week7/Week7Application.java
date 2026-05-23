package com.example.week7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.example.week7",
        "controller",
        "service",
        "repository"
})
public class Week7Application {

    public static void main(String[] args) {
        SpringApplication.run(Week7Application.class, args);
    }

}
