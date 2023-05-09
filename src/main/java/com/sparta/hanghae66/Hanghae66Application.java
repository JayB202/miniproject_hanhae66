package com.sparta.hanghae66;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing
@EnableJpaRepositories
@SpringBootApplication
public class Hanghae66Application {

    public static void main(String[] args) {
        SpringApplication.run(Hanghae66Application.class, args);
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**")
                        //.allowedOriginPatterns()
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "PATCH")
                        .allowedOriginPatterns("*")
                        //.allowedOrigins("http://localhost:8080", "http://localhost:3000", "http://127.0.0.1:3000", "http://hanghae66.s3-website.ap-northeast-2.amazonaws.com/")
                        //.allowedOrigins("*")
                        //.allowCredentials(true)
                        .exposedHeaders("ACCESS_KEY", "Authorization");

            }
        };
    }
}
