package com.fq.controller;

import com.fq.feignClient.OrderClient;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {
    @Resource
    private OrderClient orderClient;


    @GetMapping("/getOrderData")
    public String getOrderData(){
        return orderClient.orderData();
    }

}
