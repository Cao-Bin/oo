package com.cb.users.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by oo on 17-6-5.
 */
@Configuration
public class RabbitMQConfiguration {

    final static String QUEUE_NAME = "oo_queue";
    final static String EXCHANGE_NAME = "oo_exchange";
    final static String ROUTING_KEY = "oo";

    @Autowired
    private RabbitMQConfigProperties rabbitMQConfigProperties;

    // 建立一个连接容器，类型数据库的连接池。
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(rabbitMQConfigProperties.getHost(), rabbitMQConfigProperties.getPort());

        connectionFactory.setUsername(rabbitMQConfigProperties.getUsername());
        connectionFactory.setPassword(rabbitMQConfigProperties.getPassword());
        connectionFactory.setVirtualHost(rabbitMQConfigProperties.getVirtualHost());

        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    // RabbitMQ的使用入口
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    // 要求RabbitMQ建立一个队列。
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }

//    // 声明一个监听容器
//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, Receiver listenerAdapter) {
//
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(new String[]{QUEUE_NAME});
//        container.setMessageListener(listenerAdapter);
//
//        return container;
//    }
//
//    // 在spring容器中添加一个监听类
//    @Bean
//    Receiver receiver() {
//        return new Receiver();
//    }

    // 定义一个直连交换机
    @Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    // 要求队列和直连交换机绑定，指定ROUTING_KEY
    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}
