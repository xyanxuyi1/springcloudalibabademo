package com.example.orderopenfeginsentinel.fegin;

import org.springframework.stereotype.Component;

/**
 * @ClassName StockFeginServiceFallBack
 * @Description
 * @Author 徐仡
 * @Date 2022/9/29 21:58
 * @Version 1.0
 */
@Component
public class StockFeginServiceFallBack implements StockFeginService{
    @Override
    public String reduct2() {
        return "降级了";
    }
}
