package com.each.springcloud.alibaba.service.impl;

import com.each.springcloud.alibaba.dao.OrderDao;
import com.each.springcloud.alibaba.domain.Order;
import com.each.springcloud.alibaba.service.AccountService;
import com.each.springcloud.alibaba.service.OrderService;
import com.each.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;


    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     */
    @Override
    @GlobalTransactional(name = "each-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        //1.新建订单
        log.info("---------->开始新建订单");
        orderDao.create(order);

        //2.扣减库存
        log.info("---------->订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("---------->订单微服务开始调用库存，做扣减---end");

        //2.扣减账户
        log.info("---------->订单微服务开始调用账户，做扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("---------->订单微服务开始调用库存，做扣减---end");

        //4.修改订单的状态，从0到1  1代表已完成
        log.info("---------->修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("---------->修改订单状态结束");

        log.info("---------->下订单结束，┗|｀O′|┛ 嗷~~");





    }

    @Override
    public void update(Long userId, Integer status) {

    }
}
