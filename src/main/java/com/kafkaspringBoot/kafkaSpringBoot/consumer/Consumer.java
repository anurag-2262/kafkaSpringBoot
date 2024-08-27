package com.kafkaspringBoot.kafkaSpringBoot.consumer;

import org.apache.kafka.common.message.ConsumerProtocolAssignment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Consumer {


    @KafkaListener(topics = {"fruits"},groupId = "myGroup")
    public void consumeMessage0(String message){
        System.out.println("consumer1"+message);
    }

    @KafkaListener(topics = {"fruits"},groupId = "myGroup")
    public void consumeMessage1(String message){
        System.out.println("consumer2"+message);
    }
    @KafkaListener(topics = {"fruits"},groupId = "myGroup")
    public void consumeMessage2(String message){
        System.out.println("consumer3"+message);
    }

//    @KafkaListener(topics = {"fruits"},groupId = "myGroup")
//    public void consumeMessage3(String message){
//        System.out.println("consumer4"+message);
//    }
// Consumer3 is in a different consumer group
    @KafkaListener(topics = {"fruits"},groupId = "myGroup0")
    public void consumeMessage01(String message){
        System.out.println("consumer01"+message);
    }
}
