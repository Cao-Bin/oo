package com.cb.users.config;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka.cluster", ignoreInvalidFields = true)
public class KafkaClusterConfigProperties {
    /**
     * kafka集群地址
     */
    private String address;

    private KafkaConsumerConfigProperties consumer;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public KafkaConsumerConfigProperties getConsumer() {
        return consumer;
    }

    public void setConsumer(KafkaConsumerConfigProperties consumer) {
        this.consumer = consumer;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
