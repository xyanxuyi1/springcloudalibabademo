package com.order.controller;

import com.order.fegin.StockFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName OrderController
 * @Description
 * @Author 徐仡
 * @Date 2022/9/25 15:02
 * @Version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private StockFeginService stockFeginService;


    @RequestMapping("/add")
    public String add() {
        System.out.println("下单成功");
        String msg = stockFeginService.reduct();
        return "hello fegin:"+ msg;
    }
}
