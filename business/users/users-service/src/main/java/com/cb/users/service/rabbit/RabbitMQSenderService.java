package com.cb.users.service.rabbit;

/**
 * Created by oo on 17-6-10.
 */
public interface RabbitMQSenderService {
    void send(String routingKey, String message);
}
