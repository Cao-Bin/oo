package com.cb.users.service.kafka;


/**
 * Created with IntelliJ IDEA.
 * User: hengsun
 * Date: 5/8/17
 * Time: 5:56 PM
 * Description:
 */
public interface KafkaPublishService {

    void publish(String topic, Object data);
}
