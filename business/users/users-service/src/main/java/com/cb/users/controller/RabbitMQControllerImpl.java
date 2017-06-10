package com.cb.users.controller;

import com.cb.users.api.controller.RabbitMQController;
import com.cb.users.service.rabbit.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQControllerImpl implements RabbitMQController {
    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Override
    public void send(@RequestParam String routingKey, @RequestParam String message) {
        rabbitMQSender.send(routingKey, message);
    }
}
