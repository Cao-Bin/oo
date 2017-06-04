package com.cb.users.service.kafka.impl;

import com.cb.users.service.json.JsonService;
import com.cb.users.service.kafka.KafkaPublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: hengsun
 * Date: 5/8/17
 * Time: 5:56 PM
 * Description:
 */
@Service
public class KafkaPublishServiceJsonImpl implements KafkaPublishService {
    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaPublishServiceJsonImpl.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private JsonService jsonService;
    private final static Integer one_second = 1;

    @Override
    public void publish(String topic, Object data) {
        publishJsonString(topic, jsonService.toJson(data));
    }

    private void publishJsonString(String topic, String data) {
        try{
            LOGGER.info("Send data {} to kafka topic {}.", data, topic);
            ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topic, data);
            send.get(one_second, TimeUnit.SECONDS);
        }catch (Throwable e) {
            LOGGER.error("publishNotify kafka message failed", e);
        }
    }
}
