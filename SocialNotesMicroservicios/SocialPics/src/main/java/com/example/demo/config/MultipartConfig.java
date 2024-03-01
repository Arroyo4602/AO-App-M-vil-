package com.example.demo.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import jakarta.servlet.MultipartConfigElement;

@Configuration
public class MultipartConfig {
	@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // Configuración personalizada
        factory.setMaxFileSize(DataSize.ofMegabytes(10)); // Establece el tamaño máximo del archivo en 10 MB
        factory.setMaxRequestSize(DataSize.ofMegabytes(10)); // Establece el tamaño máximo de la solicitud en 10 MB
        return factory.createMultipartConfig();
    }
}
