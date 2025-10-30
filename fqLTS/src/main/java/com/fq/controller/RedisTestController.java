package com.fq.controller;

import com.fq.config.redis.RedisLock;
import com.fq.model.request.RedisReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisTestController {
    @Autowired
    RedisLock redisLock;

    @PostMapping("saveRole")
    public String saveRole(@RequestBody @Validated RedisReq req) {
        String id      = req.getId();
        redisLock.lock(id);
        return "成功结束";
    }




}
