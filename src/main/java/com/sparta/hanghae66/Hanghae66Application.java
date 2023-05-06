package com.sparta.hanghae66;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Hanghae66Application {

    public static void main(String[] args) {
        SpringApplication.run(Hanghae66Application.class, args);
    }

}
