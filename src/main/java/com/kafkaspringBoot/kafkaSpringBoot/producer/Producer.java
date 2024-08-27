package com.kafkaspringBoot.kafkaSpringBoot.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class Producer {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;
    @GetMapping("/send")
    public void sendMessage(@RequestParam String message){
        for (int i=0;i<10000;i++){
            //publisher one
        CompletableFuture<SendResult<String,Object>> future=kafkaTemplate.send("fruits",message+i);
            int finalI = i;
            future.whenCompleteAsync((result, ex)->{
            if(ex==null){
                System.out.println("sent message= [" +message+ finalI +
                        "] with offset=["+result.getRecordMetadata().offset()+"]"
                +" with partition=["+result.getRecordMetadata().partition()+"]");


            }else{
                System.out.println("Unable to send message=[ "+
                        message+"] due to: "+ex.getMessage());
            }
        });

            //publisher two
            CompletableFuture<SendResult<String,Object>> future1=kafkaTemplate.send("fruits",message+i);
            int finalI1 = i;
            future.whenCompleteAsync((result, ex)->{
                if(ex==null){
                    System.out.println("sent message= [" +message+ finalI1 +
                            "] with offset=["+result.getRecordMetadata().offset()+"]"
                            +" with partition=["+result.getRecordMetadata().partition()+"]");


                }else{
                    System.out.println("Unable to send message=[ "+
                            message+"] due to: "+ex.getMessage());
                }
            });
        }
    }
}
