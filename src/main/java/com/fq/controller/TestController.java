package com.fq.controller;

import com.fq.model.request.HelloReq;
import com.fq.model.response.HelloRsp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Random;

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

    /**
     * SSE测试
     */

    @GetMapping(value = "/stock-price", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamStockPrice() {
        SseEmitter emitter = new SseEmitter();
        // 模拟生成实时股票价格并推送给客户端
        Random random = new Random();
        new Thread(() -> {
            try {
                while (true) {
                    // 生成随机的股票价格
                    double price = 100 + random.nextDouble() * 10;
                    // 构造股票价格的消息
                    String message = String.format("%.2f", price);
                    // 发送消息给客户端
                    emitter.send(SseEmitter.event().data(message));
                    // 休眠1秒钟
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        }).start();
        return emitter;
    }

}
