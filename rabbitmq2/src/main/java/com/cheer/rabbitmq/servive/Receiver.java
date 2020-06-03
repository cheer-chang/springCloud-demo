package com.cheer.rabbitmq.servive;

import com.cheer.rabbitmq.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
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
                    value = @Queue(value = "queue666", durable = "true"),//绑定一个队列，一个项目访问一个队列。durable = "true"需要持久化
                    exchange = @Exchange(value = "${rabbitmq-config.exchange}", type = "topic"),//绑定交换器。类型一般为topic
                    key = "${rabbitmq-config.routingKey}"//绑定路由
            )
    )
    public void process(@Payload UserEntity userEntity) {//得到一个有效的消息，该类的包名要和sender传输的消息一样不然会解析失败！！！
        LOGGER.info("Receiver:{}", userEntity.toString());
    }
}
