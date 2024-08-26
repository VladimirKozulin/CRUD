package com.example.rest.rest.configuration;

import com.example.rest.rest.web.controller.interseptors.ClientControllerInterceptor;
import com.example.rest.rest.web.controller.interseptors.LoggingControllerInterceptors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationWebConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) { //Регистрация перехватчика
        registry.addInterceptor(loggingControllerInterceptors());
        registry.addInterceptor(clientControllerInterceptor())
                .addPathPatterns("/api/v1/client/**");
    }

    @Bean
    public LoggingControllerInterceptors loggingControllerInterceptors() {
        return new LoggingControllerInterceptors();
    }
    @Bean
    public ClientControllerInterceptor clientControllerInterceptor() {
        return new ClientControllerInterceptor();
    }

}
