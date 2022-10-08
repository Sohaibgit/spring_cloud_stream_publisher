package com.spring.cloud.publisher.controller;

import com.spring.cloud.publisher.entity.User;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@EnableBinding(Source.class)
public class PublisherController {

    private MessageChannel output;

    public PublisherController(MessageChannel output) {
        this.output = output;
    }

    @PostMapping("/publish")
    public User publishEvent(@RequestBody User user){
        output.send(MessageBuilder.withPayload(user).build());
        return user;
    }

}
