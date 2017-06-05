package com.cb.users.service.kafka.impl;

import com.cb.users.entity.kafka.UserKafka;
import com.cb.users.service.disruptor.DisruptorEvent;
import com.cb.users.util.JsonUtil;
import com.cb.users.util.KafkaTopicUtil;
import com.lmax.disruptor.dsl.Disruptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hengsun
 * Date: 5/9/17
 * Time: 3:37 PM
 * Description: vg消息上传
 */
@Component
public class UserKafkaTopicConsumerService {
    @Autowired
    private Disruptor<DisruptorEvent> disruptor;
     private final static Logger LOGGER = LoggerFactory.getLogger(UserKafkaTopicConsumerService.class);

    @KafkaListener(topics = KafkaTopicUtil.THIRD_IN_USER, id = "userKafkaTopicConsumer")
    public void consumerKafkaRecords(@Payload  List<ConsumerRecord<String, String>> records) {
        LOGGER.debug("Receive records count:{}", records.size());

        records.forEach(record -> {
            UserKafka userKafka = JsonUtil.toBean(record.value(), UserKafka.class);
            disruptor.getRingBuffer().publishEvent(this::translateTo, userKafka);
        });
    }

    private void translateTo(final DisruptorEvent event, long sequence, final UserKafka userKafka) {
        event.setUserKafka(userKafka);
    }
}
