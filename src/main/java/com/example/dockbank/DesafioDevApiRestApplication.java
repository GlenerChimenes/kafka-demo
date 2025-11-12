package com.example.dockbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DesafioDevApiRestApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {

        SpringApplication.run(DesafioDevApiRestApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("ENCODE =  " + passwordEncoder.encode("123456"));
    }

}
