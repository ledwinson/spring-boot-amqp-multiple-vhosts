package com.rabbit.sample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MultiVhostsApplication {

    @Bean
    public CommandLineRunner service() {
        return new MessageService();
    }

    public static void main(String[] args) {
        SpringApplication.run(MultiVhostsApplication.class, args);
    }

}
