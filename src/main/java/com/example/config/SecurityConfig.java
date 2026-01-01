package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("user").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disabled for simplicity with JSF/SOAP mix
                .authorizeRequests()
                .antMatchers("/api/**").authenticated() // Protect REST API
                .antMatchers("/javax.faces.resource/**").permitAll() // Allow JSF resources
                .anyRequest().permitAll() // Allow public access to JSF pages by default (demo)
                .and()
                .httpBasic(); // Use Basic Auth for API/SOAP testing convenience
    }
}
