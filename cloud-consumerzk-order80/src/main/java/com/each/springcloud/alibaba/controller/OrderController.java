package com.each.springcloud.alibaba.controller;

import com.each.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    private static final String INVOKE_URL = "http://cloud-provider-payment";//微服务名称

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String create(Payment payment){
        return restTemplate.getForObject(INVOKE_URL+"/payment/zk",String.class);
    }
}
