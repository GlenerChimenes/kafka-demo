package com.example.dockbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaDomainApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaDomainApplication.class, args);
    }
}
