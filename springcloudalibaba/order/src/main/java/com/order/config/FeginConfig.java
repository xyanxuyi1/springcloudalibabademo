package com.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FeginConfig
 * @Description
 * 全局配置：当我们使用Configuration将会配置作用在所有的服务提供方
 * 局部配置： 如果只想针对某一个服务进行配置，就不要加@Configuration
 * @Author 徐仡
 * @Date 2022/9/27 22:01
 * @Version 1.0
 */
@Configuration
public class FeginConfig {

    @Bean
    public Logger.Level feginLoggerLevel() {
        return Logger.Level.FULL;
    }
}
