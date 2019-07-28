package com.cheer.rabbitmq2.servive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 */
@Component
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @RabbitHandler
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "queue666", durable = "true"),
                    exchange = @Exchange(value = "exchange666", type = "topic"),
                    key = "routing.*"
            )
    )
    public void process(String msg) {
        LOGGER.info("Receiver:" + msg);
    }


}
