package com.mak.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.mak.dto.User;

import lombok.extern.slf4j.Slf4j;
// import reactor.core.publisher.Mono;

@Component
@Slf4j
public class UserConsumer {

    @Async
    @RabbitListener(queues = "${app.queue}")
    public void consumeMessagesFromQueue(User user) {
        processUser(user);

    }

    // @RabbitListener(queues = "${app.queue}")
    // public void consumeMessagesFromQueue(User user) {
    // Mono.just(user).subscribe(x -> processUser(x));

    // }

    private void processUser(User user) {
        log.info("From Q -> " + user);
    }
}
