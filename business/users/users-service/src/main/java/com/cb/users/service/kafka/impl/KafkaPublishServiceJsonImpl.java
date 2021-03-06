package com.cb.users.service.kafka.impl;

import com.cb.users.service.kafka.KafkaPublishService;
import com.cb.users.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.TimeUnit;

@Service
public class KafkaPublishServiceJsonImpl implements KafkaPublishService {
    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaPublishServiceJsonImpl.class);
    private final static Integer one_second = 1;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void publish(String topic, Object data) {
        publishJsonString(topic, JsonUtil.toJson(data));
    }

    private void publishJsonString(String topic, String data) {
        try{
            LOGGER.info("Send data {} to kafka topic {}.", data, topic);
            ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topic, data);
            send.get(one_second, TimeUnit.SECONDS);
        }catch (Throwable e) {
            LOGGER.error("publish message failed", e);
        }
    }
}
