package com.example.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class Controller {

    @GetMapping("/hello")
    public String hello(){
        return "hello API";
    }

    @GetMapping("/hey")
    public String hey(){
        return "hey API";
    }

    @GetMapping("/hi")
    public String hi(){
        return "hi API";
    }
}
