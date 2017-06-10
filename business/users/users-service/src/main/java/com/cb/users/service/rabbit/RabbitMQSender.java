package com.cb.users.service.rabbit;

import com.cb.users.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by oo on 17-6-10.
 */
@Component
public class RabbitMQSender {

    public static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String routingKey, String message) {

        amqpTemplate.convertAndSend(routingKey, message);
        LOGGER.info(JsonUtil.toJson(message));

    }
}
