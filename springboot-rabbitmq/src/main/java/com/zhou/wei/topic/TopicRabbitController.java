package com.zhou.wei.topic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description TopicRabbitController
 * @since 2020/4/6 0006 16:48
 **/
@RestController
public class TopicRabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendTopicFirst")
    public String sendTopicMessage1() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: M A N ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        manMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.man", manMap);
        return "ok";
    }

    @GetMapping("/sendTopicSecond")
    public String sendTopicSecond() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: woman is all ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> womanMap = new HashMap<>();
        womanMap.put("messageId", messageId);
        womanMap.put("messageData", messageData);
        womanMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.woman", womanMap);
        return "ok";
    }

    @RabbitListener(queues = "topic.man")
    public void topicManReceiver(Map map){
        System.out.println("topicManReceiver消费者收到消息  : " + map.toString());
    }

    @RabbitListener(queues = "topic.woman")
    public void topicWomenReceiver(Map map){
        System.out.println("topicWomenReceiver消费者收到消息  : " + map.toString());
    }

}
