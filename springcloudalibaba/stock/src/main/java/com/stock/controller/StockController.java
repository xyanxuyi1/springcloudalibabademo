package com.stock.controller;

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

    @RequestMapping("/reduct")
    public String reduct() {
        System.out.printf("扣减库存");
        return "扣减库存";
    }
}
