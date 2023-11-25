package com.Elrearning.LocalStorageConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    String uploadDir = "file:./uploads/";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configure the upload directory as a resource handler
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadDir)
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS)); // Adjust caching as needed
    }
}
