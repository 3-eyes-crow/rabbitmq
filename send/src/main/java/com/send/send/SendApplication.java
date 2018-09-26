package com.send.send;

import com.send.send.service.SendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@Slf4j
public class SendApplication {


    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey;

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Autowired
    private AmqpTemplate amqpTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SendApplication.class, args);
    }

    @RequestMapping("send")
    public void send() {
        log.info("===========send===========");
        String messageBodyBytes = "Hello, world!";
        byte i = 10;
        while (i-- > 0) {
            amqpTemplate.convertAndSend(messageBodyBytes + i, message -> {
                message.getMessageProperties().setExpiration(10000+"");
                return message;
            });
        }

    }

}
