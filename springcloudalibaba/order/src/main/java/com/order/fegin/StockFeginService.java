package com.order.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 徐仡
 * @version 1.0.0
 * @description name 指定调用rest接口对应的服务名
 * path 指定调用rest接口所在的StockController 指定的@RequestMapping
 * @date 2022/9/27 21:40
 */
@FeignClient(name = "stock-service", path = "/stock")
public interface StockFeginService {

    //声明需要调用的rest接口对应的方法
    @RequestMapping("/reduct")
    String reduct();
}