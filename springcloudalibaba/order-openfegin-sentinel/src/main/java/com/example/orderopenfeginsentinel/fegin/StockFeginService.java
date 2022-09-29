package com.example.orderopenfeginsentinel.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 徐仡
 * @version 1.0.0
 * @description
 * @date 2022/9/29 21:47
 */
@FeignClient(name = "stock-service", path = "/stock", fallback = StockFeginServiceFallBack.class)
public interface StockFeginService {


    @RequestMapping("/reduct2")
    String reduct2();
}
