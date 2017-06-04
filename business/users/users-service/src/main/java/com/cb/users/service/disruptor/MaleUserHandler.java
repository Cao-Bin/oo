package com.cb.users.service.disruptor;

import com.cb.users.entity.kafka.UserKafka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MaleUserHandler implements UserHandler {
    private final static Logger LOGGER = LoggerFactory.getLogger(MaleUserHandler.class);
    @Override
    public void handle(UserKafka userKafka) {
        LOGGER.debug(userKafka.toString());
        // TODO: 17-6-4 male user business
    }
}
