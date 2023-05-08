package com.sparta.hanghae66;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableJpaAuditing
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
                        .allowedOriginPatterns()
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "PATCH")
                        .allowedOrigins("http://localhost:8080", "http://localhost:3000", "http://127.0.0.1:3000")
                        //.allowedOrigins("*")
                        //.allowCredentials(true)
                        .exposedHeaders("ACCESS_KEY", "Authorization");

            }
        };
    }
}
