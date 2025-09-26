package com.fq.service;


import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class tryService {

//    String name = "DD";
//    String host = "blog.didispace.com";
//    String template = "hello %s, your blog is %s";
//    String result = template.formatted(name,host);


    String name   = "DD";
    String host   = "blog.didispace.com";
    String result = MessageFormat.format("hello {0}, your blog is {1}", name, host);

    @PostConstruct
    public void tt() {
        log.info("start");

        Map<String, String> map = new HashMap<>();
        map.put("kkk", "11");
        System.out.println(map.get("kkk"));
        map.put("kkk", "222");
        System.out.println(map.get("kkk"));
        System.out.println(map.containsKey(null));
        log.warn("11");

        List<String> itemIds = new ArrayList<>();
        itemIds.add("1");
        itemIds.add("");
        itemIds.add("3");
        log.warn("22");

        List<String> itemIds2 = new ArrayList<>();
        itemIds2.add("3");
        itemIds2.add("3");
        itemIds2.add("4");

        log.error("33");
        itemIds.addAll(itemIds2);
        System.out.println(itemIds.size());
        log.info("end");
    }


    public String getBlog(String text) {
        return STR."My Blog is ...\{text}";
    }


    public void testCompletableGet() throws InterruptedException, ExecutionException {

        CompletableFuture<String> cp1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "商品A";
        });

        // getNow方法测试
        System.out.println(cp1.getNow("商品B"));

        //join方法测试
        CompletableFuture<Integer> cp2 = CompletableFuture.supplyAsync((() -> 1 / 0));
        System.out.println(cp2.join());
        System.out.println("-----------------------------------------------------");
        //get方法测试
        CompletableFuture<Integer> cp3 = CompletableFuture.supplyAsync((() -> 1 / 0));
        System.out.println(cp3.get());
    }

}
