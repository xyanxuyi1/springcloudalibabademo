package com.stock.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName StockController
 * @Description
 * @Author 徐仡
 * @Date 2022/9/25 15:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/stock")
public class StockController {


    @Value("${server.port}")
    private String port ;

    @RequestMapping("/reduct")
    public String reduct() {
        System.out.println("扣减库存:"+port);
        return "扣减库存"+port;
    }
}
