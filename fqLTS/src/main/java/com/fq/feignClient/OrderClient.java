package com.fq.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "order")
public interface OrderClient {

    @GetMapping("/test/toData")
    String orderData();
}
