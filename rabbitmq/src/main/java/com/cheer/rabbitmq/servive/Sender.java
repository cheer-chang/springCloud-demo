package com.cheer.rabbitmq.servive;

import com.cheer.rabbitmq.config.RabbitmqConfig;
import com.cheer.rabbitmq.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 消息生产者
 */
@Component
public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private RabbitmqConfig rabbitmqConfig;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 通过实现 ConfirmCallback 接口，消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
     * 注意需要在配置文件增加配置
     *
     * @Param correlationData 消息唯一标识
     * @Param ack 确认结果
     * @Param cause 失败原因
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        LOGGER.info("确认回调,{}", correlationData.getId());
        if (ack) {//成功处理
            LOGGER.info("成功处理,{}", correlationData.getId());
        } else {
            LOGGER.error("未发送出去,{},原因：{}", correlationData.getId(), cause);
        }
    };

    /**
     * 通过实现 ReturnCallback 接口，启动消息失败返回，比如路由不到队列时触发回调
     * 注意需要在配置文件增加配置
     *
     * @Param message 消息主体
     * @Param replyCode 描述代码 replyCode
     * @Param replyText 描述
     * @Param exchange 消息使用的交换器
     * @Param routingKey 消息使用的路由键
     */
    final RabbitTemplate.ReturnCallback returnCallback = (message, replyCode, replyText, exchange, routingKey) -> LOGGER.error("message:{},replyCode:{},replyText:{},exchange:{},routingKey:{}", message.toString(), replyCode, replyText, exchange, routingKey);

    public void send(UserEntity userEntity) {
        rabbitTemplate.setConfirmCallback(confirmCallback);//确认回调
        rabbitTemplate.setReturnCallback(returnCallback);//返回回调
        LOGGER.info("Sender:{}", userEntity.toString());
        CorrelationData correlationData = new CorrelationData();//创建唯一消息标识
        correlationData.setId("666@" + UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(rabbitmqConfig.getExchange(), rabbitmqConfig.getRoutingKey(), userEntity, correlationData);
    }
}
