package com.manjula.securityjwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public Message home() {
        return new Message("Hello world! This is the home page");
    }

    @GetMapping("/hello")
    public Message hello() {
        return new Message("Hello World");
    }

}
