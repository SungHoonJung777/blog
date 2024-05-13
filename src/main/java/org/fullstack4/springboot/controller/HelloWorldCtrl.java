package org.fullstack4.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldCtrl {
    @GetMapping("/hello")
    public String hello(){
        return "hello  ";
    }
}
