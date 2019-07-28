package com.cheer.rabbitmq.controller;

import com.cheer.rabbitmq.servive.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SenderController {


    @Autowired
    private Sender sender;

    @RequestMapping("/send")
    public String send(String msg) {
        sender.send(msg);
        return msg;
    }

}
