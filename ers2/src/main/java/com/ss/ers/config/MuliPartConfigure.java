package com.ss.ers.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Configuration
public class MuliPartConfigure {
    
    @Bean
    public MultipartConfigElement multipartConfigElement () {
        MultipartConfigFactory factory = new MultipartConfigFactory ();
        factory.setMaxFileSize (DataSize.parse ("100MB"));
        factory.setMaxRequestSize (DataSize.parse ("100MB"));
        return factory.createMultipartConfig ();
    }
    
}
