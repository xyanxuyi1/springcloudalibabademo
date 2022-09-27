package com.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RibbonRandomRuleConfig
 * @Description
 * @Author 徐仡
 * @Date 2022/9/27 15:50
 * @Version 1.0
 */
@Configuration
public class RibbonRandomRuleConfig {


    @Bean
    public IRule iRule() {
        return new RandomRule();
    }
}
