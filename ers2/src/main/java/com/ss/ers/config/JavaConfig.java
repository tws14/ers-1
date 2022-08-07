package com.ss.ers.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    
    @Bean
    public ModelMapper modelMapper () {
        
        ModelMapper modelMapper = new ModelMapper ();
        
        return modelMapper;
    }
    
}
