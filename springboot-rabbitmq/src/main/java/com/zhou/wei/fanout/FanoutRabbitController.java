package com.zhou.wei.fanout;

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
 * @description FanoutRabbitController
 * @since 2020/4/6 0006 16:59
 **/
@RestController
public class FanoutRabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: testFanoutMessage ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("fanoutExchange", null, map);
        return "ok";
    }

    @RabbitListener(queues = "fanout.A")
    public void FanoutReceiverA(Map testMessage){
        System.out.println("FanoutReceiverA消费者收到消息  : " +testMessage.toString());
    }

    @RabbitListener(queues = "fanout.B")
    public void FanoutReceiverB(Map testMessage){
        System.out.println("FanoutReceiverB消费者收到消息  : " +testMessage.toString());
    }

    @RabbitListener(queues = "fanout.C")
    public void FanoutReceiverC(Map testMessage){
        System.out.println("FanoutReceiverC消费者收到消息  : " +testMessage.toString());
    }

}
