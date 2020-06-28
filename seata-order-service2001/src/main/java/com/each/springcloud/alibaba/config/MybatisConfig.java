package com.each.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.each.springcloud.alibaba.dao"})
public class MybatisConfig {
}
