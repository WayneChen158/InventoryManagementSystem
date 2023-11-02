//package com.atilaBiosystems.InventoryManagementSystem.Security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // Allow CORS for all endpoints
//                .allowedOrigins("http://localhost:3030") // Replace with your frontend URL
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("Authorization", "Content-Type")
//                .exposedHeaders("Authorization", "Content-Type")
//                .allowCredentials(true); // If your frontend sends credentials (cookies or tokens)
//    }
//}