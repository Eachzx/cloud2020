package com.each.springcloud.alibaba.controller;

import com.each.springcloud.sercive.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public  String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;

    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "payment_Global_FullbackMethod",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand(fallbackMethod = "payment_Global_FullbackMethod")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        int age = 10/0;
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        return result;
    }


    public String paymentTimeOutFullbackMethod(Integer id){
        return "我是消费者80，对方支付服务系统繁忙，请10秒钟后再试或者自己运行错误请检测自己，o(≧口≦)o";
    }
    public String payment_Global_FullbackMethod(Integer id){
        return "Global异常处理信息，请稍后再试，o(≧口≦)o";
    }
}
