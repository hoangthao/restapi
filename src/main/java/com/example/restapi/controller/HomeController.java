package com.example.restapi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

//@CrossOrigin
@RestController
public class HomeController {

    @GetMapping("/")
    public Mono<String> home() {
        return Mono.just("hello rest api");
    }
}
