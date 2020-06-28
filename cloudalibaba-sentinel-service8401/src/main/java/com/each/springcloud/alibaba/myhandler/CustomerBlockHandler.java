package com.each.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.each.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException e){
        return new CommonResult(444,"按客户自定义,global CustomerBlockHandler----1");
    }

    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(444,"按客户自定义,global CustomerBlockHandler----2");
    }
}
