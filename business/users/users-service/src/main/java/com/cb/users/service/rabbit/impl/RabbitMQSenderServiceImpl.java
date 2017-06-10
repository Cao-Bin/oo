package com.cb.users.service.rabbit.impl;

import com.cb.users.service.rabbit.RabbitMQSenderService;
import com.cb.users.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by oo on 17-6-10.
 */
@Service
public class RabbitMQSenderServiceImpl implements RabbitMQSenderService {

    public static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQSenderServiceImpl.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void send(String routingKey, String message) {

        amqpTemplate.convertAndSend(routingKey, message);
        LOGGER.info(JsonUtil.toJson(message));

    }
}
