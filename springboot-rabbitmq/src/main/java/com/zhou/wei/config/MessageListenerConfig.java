package com.zhou.wei.config;

import com.zhou.wei.direct.DirectRabbitConfig;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description 消息确保机制添加
 * @since 2020/4/6 0006 23:42
 **/
@Configuration
public class MessageListenerConfig {
    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private DirectReceiver directReceiver;//Direct消息接收处理类
    //@Autowired
    //FanoutReceiverA fanoutReceiverA;//Fanout消息接收处理类A
    @Autowired
    DirectRabbitConfig directRabbitConfig;
    //@Autowired
    //FanoutRabbitConfig fanoutRabbitConfig;
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        // RabbitMQ默认是自动确认，这里改为手动确认消息
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setQueues(directRabbitConfig.TestDirectQueue());
        container.setMessageListener(directReceiver);
        //container.addQueues(fanoutRabbitConfig.queueA());
        //container.setMessageListener(fanoutReceiverA);
        return container;
    }
}
