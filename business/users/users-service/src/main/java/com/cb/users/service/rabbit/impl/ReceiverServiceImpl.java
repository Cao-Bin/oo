package com.cb.users.service.rabbit.impl;

import com.cb.users.service.rabbit.ReceiverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Created by oo on 17-6-10.
 */
@Service
@RabbitListener(queues = "oo_queue")
public class ReceiverServiceImpl implements ReceiverService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageListener.class);

    @RabbitHandler
    @Override
    public void process(String message) {
        LOGGER.info("received rabbitmq message: {}", message);
    }
}
