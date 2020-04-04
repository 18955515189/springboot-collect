package com.zhou.wei.config;

import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description Kafka消息value反序列化
 * @since 2020/4/4 0004 22:03
 **/
public class DecodeingKafka implements Deserializer<Object> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        return BeanUtils.BytesToObject(bytes);
    }

    @Override
    public void close() {

    }
}
