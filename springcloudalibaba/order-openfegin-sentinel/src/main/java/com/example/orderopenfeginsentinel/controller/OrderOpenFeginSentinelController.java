package com.example.orderopenfeginsentinel.controller;

import com.example.orderopenfeginsentinel.fegin.StockFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderOpenFeginSentinelController
 * @Description
 * @Author 徐仡
 * @Date 2022/9/29 21:48
 * @Version 1.0
 */
@RestController
@RequestMapping("/openfeginsentinel")
public class OrderOpenFeginSentinelController {

    @Autowired
    private StockFeginService stockFeginService;

    @RequestMapping("/add")
    public String add() {
        System.out.println("下单成功");
        String s = stockFeginService.reduct2();

        return "hello fegin: "+ s;
    }
}
