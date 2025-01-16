package com.kwanse.khan0116.common.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JwtAuthFilter> jwtFilterRegistration(JwtAuthFilter jwtFilter) {
        FilterRegistrationBean<JwtAuthFilter> regBean = new FilterRegistrationBean<>(jwtFilter);
        regBean.addUrlPatterns("/*"); // 모든 경로에 필터 적용
        regBean.setOrder(1);
        return regBean;
    }
}
