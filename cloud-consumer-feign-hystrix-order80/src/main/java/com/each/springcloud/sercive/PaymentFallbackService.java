package com.each.springcloud.sercive;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "---------PaymentFallbackService full back-paymentInfo_OK";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "---------PaymentFallbackService full back-paymentInfo_Timeout";
    }
}
