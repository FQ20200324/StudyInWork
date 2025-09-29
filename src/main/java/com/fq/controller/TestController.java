package com.fq.controller;

import com.fq.model.request.HelloReq;
import com.fq.model.response.HelloRsp;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping ("/Hello")
    public HelloRsp Hello(@RequestBody HelloReq request){
        if ("1".equals(request.getType())){
            HelloRsp helloRsp = new HelloRsp();
            helloRsp.setPrt("Hello World!");
            return helloRsp;
        }
        return new HelloRsp();
    }

}
