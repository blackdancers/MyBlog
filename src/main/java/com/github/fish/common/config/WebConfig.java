package com.github.fish.common.config;

import com.github.fish.common.instantiator.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 启用控制器过滤
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //排除特殊路径
        registry.addInterceptor(new LoginInterceptor()).
                addPathPatterns("/admin/**").
                excludePathPatterns("/admin").
                excludePathPatterns("/admin/login");
    }
}
