package com.sheepion.simplequiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sheepion.simplequiz.common.interceptor.SqlTimeFieldsInterceptor;

/**
 * MyBatis配置类
 */
@Configuration
public class MybatisConfig {

    /**
     * 注册SQL时间字段自动填充拦截器
     */
    @Bean
    SqlTimeFieldsInterceptor sqlTimeFieldsInterceptor() {
        return new SqlTimeFieldsInterceptor();
    }
    
} 