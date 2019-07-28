package com.cheer.feign.service.impl;

import com.cheer.feign.service.IHelloService;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements IHelloService {
    @Override
    public String hello() {
        return "服务器故障了";
    }
}