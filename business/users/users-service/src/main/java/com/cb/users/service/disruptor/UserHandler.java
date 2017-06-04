package com.cb.users.service.disruptor;

import com.cb.users.entity.kafka.UserKafka;

public interface UserHandler {
    void handle(UserKafka userKafka);
}
