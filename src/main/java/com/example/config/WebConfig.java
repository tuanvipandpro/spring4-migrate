package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.example.controller", "com.example.soap" })
public class WebConfig extends WebMvcConfigurerAdapter {
   // Can implement addResourceHandlers here if needed for static resources
}
