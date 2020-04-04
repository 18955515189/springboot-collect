package com.zhou.wei.config;

import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description Kafka消息value序列化
 * @since 2020/4/4 0004 22:01
 **/
public class EncodeingKafka implements Serializer<Object> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Object o) {
        return BeanUtils.ObjectToBytes(o);
    }

    @Override
    public void close() {

    }
}
