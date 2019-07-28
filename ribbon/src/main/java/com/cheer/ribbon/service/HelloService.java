package com.cheer.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HelloService {


    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "serviceFailure")
    public String hello() {
        return restTemplate.getForObject("http://CLIENT/hello", String.class);
    }


    public String serviceFailure() {
        return "服务器暂时故障";
    }
}
