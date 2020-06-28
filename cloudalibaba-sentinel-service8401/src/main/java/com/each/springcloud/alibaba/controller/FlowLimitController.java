package com.each.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "--------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName()+"..testB");
        return "--------testB";
    }


    @GetMapping("/testD")
    public String testD(){
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.info("测试RT..testD");
        log.info("testD 异常比例");
        int age = 10/0;
        return "--------testD";
    }

    @GetMapping("/testE")
    public String testE(){
        log.info("testE 异常异常数");
        int age = 10/0;
        return "--------testE 异常数";
    }

    @GetMapping("/testHotkey")
    @SentinelResource(value = "testHotkey",blockHandler = "deal_testHotkey",fallback = "err_testHotkey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,@RequestParam(value = "p2",required = false) String p2){
        int age = 10/0;
        return "...testHotkey";
    }

    public String deal_testHotkey(String p1, String p2, BlockException e){
        return "----------服务请求失败";
    }

    public String err_testHotkey(String p1, String p2){
        return "系统错误";
    }
}
