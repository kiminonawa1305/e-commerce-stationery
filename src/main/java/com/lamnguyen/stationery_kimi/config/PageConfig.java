package com.lamnguyen.stationery_kimi.config;

import com.lamnguyen.stationery_kimi.filter.LoadingCategoryFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

public class PageConfig {
    @Bean
    public FilterRegistrationBean<LoadingCategoryFilter> loggingFilter() {
        FilterRegistrationBean<LoadingCategoryFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new LoadingCategoryFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }
}
