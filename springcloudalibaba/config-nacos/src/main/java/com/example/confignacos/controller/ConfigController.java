package com.example.confignacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ConfigController
 * @Description
 * @Author 徐仡
 * @Date 2022/9/28 15:04
 * @Version 1.0
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {


    @Value("${user.name}")
    public String username;

    @RequestMapping("/show")
    public String show() {
        return username;
    }
}
