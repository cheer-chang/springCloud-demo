package com.cheer.feign.service;


import com.cheer.feign.service.impl.HelloServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "CLIENT", fallback = HelloServiceImpl.class)
public interface IHelloService {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String hello();
}
