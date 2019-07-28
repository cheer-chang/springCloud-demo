package com.cheer.rabbitmq.servive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

/**
 * 消息生产者
 */
@Component
public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg) {
        LOGGER.info("Sender:" + msg);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("666" + UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("exchange666", "routing.666", msg, correlationData);
    }

}
