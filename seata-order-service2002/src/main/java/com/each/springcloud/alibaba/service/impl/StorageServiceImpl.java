package com.each.springcloud.alibaba.service.impl;

import com.each.springcloud.alibaba.dao.StorageDao;
import com.each.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("---->Storage-Service中扣减库存开始");
        storageDao.decrease(productId,count);
        log.info("---->Storage-Service中扣减库存结束");

    }
}
