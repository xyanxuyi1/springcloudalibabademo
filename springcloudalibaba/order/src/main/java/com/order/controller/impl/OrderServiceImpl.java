package com.order.controller.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.order.controller.service.OrderService;
import com.order.domain.User;
import org.springframework.stereotype.Service;

/**
 * @ClassName OrderServiceImpl
 * @Description
 * @Author 徐仡
 * @Date 2022/9/29 17:03
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    @SentinelResource(value = "getUser")
    public User getUser() {
        return new User("查询用户成功");
    }
}
