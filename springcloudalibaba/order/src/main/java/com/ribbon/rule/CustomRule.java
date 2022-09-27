package com.ribbon.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @ClassName CustomRule
 * @Description ribbon自定义负载均衡策略
 * @Author 徐仡
 * @Date 2022/9/27 16:40
 * @Version 1.0
 */
public class CustomRule extends AbstractLoadBalancerRule {
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {

        ILoadBalancer loadBalancer = this.getLoadBalancer();

        // 获取当前请求的服务实例
        List<Server> reachableServers = loadBalancer.getReachableServers();

        //获取当前线程随机数
        int i = ThreadLocalRandom.current().nextInt(reachableServers.size());

        Server server = reachableServers.get(i);

        return server;
    }
}
