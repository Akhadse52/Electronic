package com.lcw.electronic_store.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class projectConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
