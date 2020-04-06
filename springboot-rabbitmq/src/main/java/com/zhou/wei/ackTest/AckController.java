package com.zhou.wei.ackTest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description rabbitMq消息确保机制测试
 * @since 2020/4/6 0006 23:16
 **/
@RestController
public class AckController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendAckA")
    public String sendAckA(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "no exchange and no routing key";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        rabbitTemplate.convertAndSend("TestDirectExchangeExtra", "TestDirectRoutingExtra", map);
        return "ok";
    }


    @GetMapping("/sendAckB")
    public String sendAckB(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "no exchange and no routing key";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRoutingExtra", map);
        return "ok";
    }

    @GetMapping("/sendAckC")
    public String sendAckC(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "no exchange and no routing key";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        rabbitTemplate.convertAndSend("TestDirectExchangeExtra", "TestDirectRouting", map);
        return "ok";
    }

}
