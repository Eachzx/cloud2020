package com.each.springcloud.alibaba.controller;

import com.each.springcloud.entities.CommonResult;
import com.each.springcloud.entities.Payment;
import com.each.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果：{}",result);
        if (result>0){
            return new CommonResult(200,"插入数据库成功!serverPort"+serverPort,result);
        }else {
            return new CommonResult(500,"插入数据库失败",null);
        }
    }


    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果：{}",payment);
        if (payment!=null){
            return new CommonResult(200,"查询成功!serverPort"+serverPort,payment);
        }else {
            return new CommonResult(500,"没有此数据",null);
        }
    }


}
