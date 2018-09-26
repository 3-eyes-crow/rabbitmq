package com.recieve.recieve.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: xqq
 * @Date: 2018/9/18 16:44
 * @Description:
 */
@Component
@RabbitListener(queues = "Queue2")
public class ScheduleReceiver {

    @RabbitHandler
    public void getMessage(String msg){
        System.out.println(msg);
    }
}
