package com.cb.users.service.disruptor;

import com.cb.users.entity.kafka.UserKafka;
import com.lmax.disruptor.WorkHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//多实例
@Component
public class UserWorkHandler implements WorkHandler<DisruptorEvent>{
    private final static Logger LOGGER = LoggerFactory.getLogger(UserWorkHandler.class);

    @Autowired
    private UserHandlerFactory userHandlerFactory;

    @Override
    public void onEvent(final DisruptorEvent event) throws Exception {
        UserKafka userKafka = event.getUserKafka();
        LOGGER.info("Receive a  event {}", userKafka);
        try{
            // TODO: 17-6-4 business
            userHandlerFactory.handle(userKafka);

        }catch (Exception e){
            LOGGER.warn("DisruptorEvent processing failure.", e);
        }finally {
            event.clear();
        }
    }


}
