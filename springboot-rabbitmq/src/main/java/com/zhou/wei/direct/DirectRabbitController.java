package com.zhou.wei.direct;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
 * @description DirectRabbitController
 * @since 2020/4/6 0006 16:05
 **/
@RestController
public class DirectRabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendDirectMessage/{message}")
    public void sendDirectMessage(@PathVariable("message") String message){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = message;
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
    }

    @RabbitListener(queues = "TestDirectQueue")
    public void consumerDirectMessage(Map map){
        System.out.println("DirectReceiver消费者收到消息  : " + map.toString());
    }

}
