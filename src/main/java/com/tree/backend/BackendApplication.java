
package com.tree.backend;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.tree.backend.config.JwtFilter;

@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
public class BackendApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/rest/*");
        return registrationBean;
    }

    @Bean
    public CacheManager cacheManageer() {
        GuavaCacheManager cacheManager = new GuavaCacheManager("tree");
        return cacheManager;
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
