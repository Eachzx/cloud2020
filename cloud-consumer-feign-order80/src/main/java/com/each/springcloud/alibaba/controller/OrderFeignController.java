package com.each.springcloud.alibaba.controller;

import com.each.springcloud.entities.CommonResult;
import com.each.springcloud.entities.Payment;
import com.each.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public CommonResult<String> PaymentFeignTimeout(){
//        openfeign默认等待一秒

        return new CommonResult<String>(200,paymentFeignService.PaymentFeignTimeout());
    }
}
