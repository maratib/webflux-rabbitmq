package com.mak.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mak.config.MessagingConfig;
import com.mak.dto.User;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserService {

    @Autowired
    private RabbitTemplate template;

    public Mono<ResponseEntity<?>> addUser(User user) {
        // System.out.println(user);

        return Mono.fromCallable(() -> {
            sendToQueue(user);
            return ResponseEntity.accepted().build();
            // return new ResponseEntity<Void>(HttpStatus.CREATED);
        });
    }

    private void sendToQueue(User user) {
        log.info("To Q -> " + user);
        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, user);
    }

}
