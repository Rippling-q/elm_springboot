package com.example.elm_springboot.config.CrossOriginHandler;

import jakarta.annotation.Resource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Resource
    private CorsFilter corsFilter;

    @Bean
    public FilterRegistrationBean<CorsFilter> filterRegistrationBean() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(corsFilter);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
