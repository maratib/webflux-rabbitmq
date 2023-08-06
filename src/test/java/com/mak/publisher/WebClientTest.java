package com.mak.publisher;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.mak.dto.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebClientTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void parallelRequestTest() {

        Flux.just(1, 2, 3, 4, 5)
                .parallel()
                .subscribe(id -> getWebClient(id));

    }

    private void getWebClient(int id) {
        User user = new User(id, "Maratib Ali Khan", new Date(1532516399000l));
        webTestClient.post()
                .uri("/user/publisher/create")
                .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .accept(MediaType.APPLICATION_NDJSON)
                .body(Mono.just(user), User.class)
                .exchange()
                .expectStatus().isAccepted();
    }

}
