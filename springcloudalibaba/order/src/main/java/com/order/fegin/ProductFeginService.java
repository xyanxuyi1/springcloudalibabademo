package com.order.fegin;

import com.order.config.FeginConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 徐仡
 * @version 1.0.0
 * @description
 *   局部增加日志FeginClient 增加configuration属性，全局则去掉
 * @date 2022/9/27 22:04
 */
@FeignClient(name = "prodcut-service", path = "/product")
public interface ProductFeginService {

    @RequestMapping("/{id}")
    String get(@PathVariable("id") Integer id);
}
