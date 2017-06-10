package com.cb.users.service.kafka;


public interface KafkaPublishService {

    void publish(String topic, Object data);
}
