package com.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.shaded.com.google.gson.JsonObject;
import com.order.controller.service.OrderService;
import com.order.domain.Result;
import com.order.domain.User;
import com.order.fegin.ProductFeginService;
import com.order.fegin.StockFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

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

    @Autowired
    private ProductFeginService productFeginService;

    @Autowired
    private OrderService orderService;


    @RequestMapping("/add")
    public String add() {
        System.out.println("下单成功");
        String s = stockFeginService.reduct();
        String p = productFeginService.get(1);
        return "hello fegin:" + s + p;
    }

    @RequestMapping("/sentinel")
    public String sentinel() {
        return "hello sentinel";
    }

    @RequestMapping("/flow")
//    @SentinelResource(value = "flow" , blockHandler = "flowBlockHandler")
    public String flow() {
        return "hello flow";
    }

    public String flowBlockHandler(BlockException exception) {
        return "流控";
    }

    @RequestMapping("/flowTread")
    @SentinelResource(value = "flowTread", blockHandler = "flowBlockHandler")
    public String flowTread() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return "hello flow";
    }

    @RequestMapping("/addOrder")
    public String addOrder() {
        System.out.println("下单成功");
        return "生成订单";
    }

    @RequestMapping("/getOrder")
    public String getOrder() {
        return "查询订单";
    }

    @RequestMapping("/test1")
    public User test1() {
        return orderService.getUser();
    }

    @RequestMapping("/test2")
    public User test2() {
        return orderService.getUser();
    }

    @RequestMapping("/err")
    @SentinelResource(value ="err", blockHandler = "errBlockHandler")
    public String err() {
        int i = 1 / 0;
        return "hello";
    }

    public String errBlockHandler(BlockException e) {
        return "服务降级了";
    }

}
