package com.cb.users.config;

import com.cb.users.service.disruptor.DisruptorEvent;
import com.cb.users.service.disruptor.UserWorkHandler;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
public class DisruptorConfiguration {

    private final int workPoolSize = 6;
    @Autowired
    private ApplicationContext context;

    @Bean
    Disruptor disruptor() {
        Disruptor<DisruptorEvent> disruptor = new Disruptor<>(DisruptorEvent::new, 1024,   Executors.defaultThreadFactory(), ProducerType.SINGLE, new SleepingWaitStrategy());
        disruptor.handleEventsWithWorkerPool(getBRequestHandlers());
        disruptor.start();
        return disruptor;
    }

    //为了获取多个实例
    UserWorkHandler[] getBRequestHandlers() {
        UserWorkHandler[] handlers = new UserWorkHandler[workPoolSize];
        for(int i = 0; i < workPoolSize; i ++) {
            handlers[i] = context.getBean(UserWorkHandler.class);
        }
        return handlers;
    }

}
