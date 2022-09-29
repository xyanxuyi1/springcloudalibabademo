package com.example.sentinel_demo.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.example.sentinel_demo.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName HelloController
 * @Description
 * @Author 徐仡
 * @Date 2022/9/28 17:49
 * @Version 1.0
 */

@RestController
@RequestMapping("/sentinel")
@Slf4j
public class HelloController {

    private static final String RESOURCE_NAME = "hello world";
    private static final String USER_RESOURCE_NAME = "user";
    private static final String DEGRADE_RESOURCE_NAME = "degrade";

    @RequestMapping(value = "/hello")
    public String hello() {
        Entry entry = null;
        try {
            //sentinel真醉资源进行限制
            entry = SphU.entry(RESOURCE_NAME);
            //被保护的业务逻辑
            String str = "hello world";
            log.info("=============" + str + "=============");
            return str;
        } catch (BlockException el) {
            //资源访问阻止 被限制或者被降级
            //进行相应的处理操作
            log.info("block");
            return "被监控了";
        } catch (Exception ex) {
            //若需要配置降级规则 则需要通过这种方式记录业务异常
            Tracer.traceEntry(ex, entry);
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return null;
    }

    /**
     * @ClassName HelloController
     * @Description 定义流控规则
     * @Author xuge
     * @Date 2022/9/28 18:02
     * @Version 1.0
     */
    @PostConstruct
    private static void initFlowRules() {
        //流控规则
        List<FlowRule> rules = new ArrayList<>();
        //流控
        FlowRule rule = new FlowRule();
        //设置要保护的资源
        rule.setResource(RESOURCE_NAME);
        //设置流控规则QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //设置受保护的资源阀值
        //set limit QPS to 20
        rule.setCount(1);
        rules.add(rule);


        //通过注解SentinelResource来定义资源并配置降级和流控方法
        FlowRule rule2 = new FlowRule();
        rule2.setResource(USER_RESOURCE_NAME);
        rule2.setGrade(RuleConstant.FLOW_GRADE_QPS);

        rule2.setCount(1);
        rules.add(rule2);

        //加载配置好的规则
        FlowRuleManager.loadRules(rules);
    }

    /*
     * @description: 定义sentinel降级规则
     * @author: xuge
     * @date: 2022/9/29 10:59
     **/
    @PostConstruct
    private static void initDegradeRule() {
        //流控规则
        List<DegradeRule> rules = new ArrayList<>();
        //流控
        DegradeRule rule = new DegradeRule();
        //设置要保护的资源
        rule.setResource(DEGRADE_RESOURCE_NAME);
        //设置降级规则策略
        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        //设置异常数
        //触发熔断异常数 2
        rule.setCount(2);
        //触发熔断最小请求数
        rule.setMinRequestAmount(2);
        //统计时长 1分钟
        rule.setStatIntervalMs(60*1000);

        //一分钟内：执行了2次，出现了2次异常，就回触发熔断

        //熔断持续时长： 单位s
        //一旦触发熔断  再次请求对应的接口，就会直接调用降级方法
        //10秒过了之后 --半开状态：恢复接口的调用，但是如果第一次请求就异常，就回再次熔断，不会根据设置的条件判定
        rule.setTimeWindow(10);

        rules.add(rule);

        //加载配置好的降级规则
        DegradeRuleManager.loadRules(rules);
    }

    @RequestMapping("/degrade")
    @SentinelResource(value = DEGRADE_RESOURCE_NAME, entryType = EntryType.IN, blockHandler = "blockHandlerForFb")
    public User degrade(String id) throws InterruptedException {
        throw new RuntimeException("异常");
    }

    /*
     * @description: 熔断后调用方法
     * @author: xuge
     * @date: 2022/9/29 11:45
     **/
    public User blockHandlerForFb(String id, BlockException ex) {
        return new User("降级");
    }

    /**
     * 类描述：
     *
     * @ClassName HelloController
     * @Description
     * 流控方法blockHandlerForGetUser，必须在同一个类中,如果不在同一个类中blockHanderClass，但是方法必须是static
     * 当接口出现异常 ，可以指定fallback方法处理
     *  如果不在同一个类中 fallbackclass 但方法必须是static
     *
     * 若fallback blockHandler同时配置，则blockHandler优先级高于fallback
     * @Author xuge
     * @Date 2022/9/28 22:51
     * @Version 1.0
     */
    @RequestMapping("/user")
    @SentinelResource(value = USER_RESOURCE_NAME, fallback = "fallbackHandleForGetUser", blockHandler = "blockHandlerForGetUser")
    public User getUser(String id) {
        int i = 1/0;
        return new User("xuge");
    }

    public User fallbackHandleForGetUser(String id, Throwable exception) {
        exception.printStackTrace();
        return new User("异常处理");
    }


    /*
     * @description:
     * 一定要public
     * 返回值要和原方法保持一致
     * 可以在参数最后添加一个异常类
     * @author: xuge
     * @date: 2022/9/28 22:55
     * @param:
     * @return:
     **/
    public User blockHandlerForGetUser(String id, BlockException exception) {
        exception.printStackTrace();
        return new User("流控");
    }
}
