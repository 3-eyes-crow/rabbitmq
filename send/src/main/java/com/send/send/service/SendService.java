package com.send.send.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xqq
 * @Date: 2018/9/17 11:20
 * @Description:
 */
@Service
@Slf4j
public class SendService {

    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey;

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMsg(String exchange, String routingKey, String data) {
        amqpTemplate.convertAndSend(exchange, routingKey, data);
    }

    public void sendMsg(String data) {
        try {
            amqpTemplate.convertAndSend(exchange, routingKey, data);
        } catch (Exception e) {
            log.error("发送失败", e);
        }
    }

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User("a", 1));
        list.add(new User("b", 2));
        list.stream().filter(e -> e.getAge() == 1)
                .forEach(e->e.setAge(4));
        System.out.println(list);
    }

}
