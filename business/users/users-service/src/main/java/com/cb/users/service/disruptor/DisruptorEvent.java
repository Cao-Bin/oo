package com.cb.users.service.disruptor;

import com.cb.users.entity.kafka.UserKafka;

public class DisruptorEvent {
    private UserKafka userKafka;

    public UserKafka getUserKafka() {
        return userKafka;
    }

    public void setUserKafka(UserKafka userKafka) {
        this.userKafka = userKafka;
    }

    public void clear() {
        userKafka = null;
    }
}
