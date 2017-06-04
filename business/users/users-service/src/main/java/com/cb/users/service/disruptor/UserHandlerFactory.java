package com.cb.users.service.disruptor;

import com.cb.users.entity.enums.Gender;
import com.cb.users.entity.kafka.UserKafka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Component
public class UserHandlerFactory {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserHandlerFactory.class);
    @Autowired
    private MaleUserHandler maleUserHandler;
    @Autowired
    private FemaleUserHandler femaleUserHandler;

    private Map<Gender, UserHandler> route;

    @PostConstruct
    private void initRoute() {
        if (route == null) {
            route = new HashMap<>();
            route.put(Gender.MALE, maleUserHandler);
            route.put(Gender.FEMALE, femaleUserHandler);
        }
    }



    public void handle(UserKafka userKafka) {
        // TODO: 17-6-4 根据用户类型处理不同用户
        UserHandler handler = route.get(userKafka.getGender());
        if (handler == null) {
            LOGGER.warn("UnSupport userKafka  {}", userKafka);
        } else {
            handler.handle(userKafka);
        }

    }


}

