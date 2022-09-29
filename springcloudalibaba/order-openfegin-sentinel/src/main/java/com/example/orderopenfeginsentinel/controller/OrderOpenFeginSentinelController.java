package com.example.orderopenfeginsentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.orderopenfeginsentinel.fegin.StockFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * @Description 热点规则必须使用  @SentinelResource使用
     * @Author xuge
     * @Date 2022/9/29 22:30
     * @Version 1.0
     */
    @RequestMapping("/get/{id}")
    @SentinelResource(value = "getById", blockHandler = "HotBlockHandler")
    public String getById(@PathVariable("id") Integer id) throws InterruptedException {
        System.out.println("正常访问");
        return "正常访问";
    }


    public String HotBlockHandler(@PathVariable("id") Integer id, BlockException e) throws InterruptedException {
        return "热点异常处理";
    }

}
