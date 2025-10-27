package com.fq.controller;

import com.fq.model.request.HelloReq;
import com.fq.model.response.HelloRsp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @PostMapping("/Hello")
    public HelloRsp Hello(@RequestBody HelloReq request) {
        if ("1".equals(request.getType())) {
            HelloRsp helloRsp = new HelloRsp();
            log.info("This should show traceId");
            helloRsp.setPrt("Hello World!");
            return helloRsp;
        }
        return new HelloRsp();
    }

    @GetMapping("/Test")
    public HelloRsp TestH() {
        HelloRsp helloRsp = new HelloRsp();
        log.info("should show traceId");
        helloRsp.setPrt("Hello World!");
        return helloRsp;

    }


}
