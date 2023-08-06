package com.mak.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mak.dto.User;
import com.mak.service.UserService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user/publisher")
public class UserPublisher {

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    private UserService userService;

    @GetMapping()
    public String index() {
        // getWebClient(1);
        System.out.println("Index method called");
        return "API V1 : " + appVersion;
        // throw new Exception("Some Exception");

    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Mono<ResponseEntity<?>> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

}
