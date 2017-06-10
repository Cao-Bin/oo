package com.cb.users.service.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by oo on 17-6-10.
 */
@Component
@RabbitListener(queues = "oo_queue")
public class Receiver {

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    @RabbitHandler
    public void process(String message) {
        LOGGER.info("received rabbitmq message: {}", message);
    }
}
