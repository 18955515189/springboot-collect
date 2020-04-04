package com.zhou.wei;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description Kafka引导类
 * @since 2020/4/4 0004 19:39
 **/
@SpringBootApplication
@RestController
public class KafkaApplication {

    public static void main(String[] args){
        SpringApplication.run(KafkaApplication.class,args);
    }

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 简单类型
     * @param msg
     * @return
     */
    @RequestMapping(value = "/send/{msg}",method = RequestMethod.GET)
    public String send(@PathVariable("msg") String msg){
        kafkaTemplate.send("demo", msg); //使用kafka模板发送信息
        return "success";
    }

    @KafkaListener(topics = "demo")
    public void listen (String record){
        System.out.printf("获取消费信息："+record);
    }

    /**
     * 复杂类型
     */
    @RequestMapping(value = "/sendObj",method = RequestMethod.GET)
    public String sendObj(){
        kafkaTemplate.send("topic_001", new Student("胖哥",27)); //使用kafka模板发送信息
        return "success";
    }

    @KafkaListener(topics = "topic_001")
    public void listenObj (Student record){
        System.out.printf("获取消费信息："+record);
    }

    @AllArgsConstructor
    @Data
    static class Student implements Serializable{
        private String name;
        private Integer age;
    }

}
