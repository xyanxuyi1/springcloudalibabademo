package com.example.confignacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ConfigNacosApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext run = SpringApplication.run(ConfigNacosApplication.class, args);
        for (;;) {
            String username = run.getEnvironment().getProperty("user.name");
            String userage = run.getEnvironment().getProperty("user.age");
            String config = run.getEnvironment().getProperty("user.config");
            System.out.println("user name: " + username + ": user age: " + userage + ": config: " +config);
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
