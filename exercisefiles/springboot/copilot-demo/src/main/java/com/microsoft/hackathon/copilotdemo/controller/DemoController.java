package com.microsoft.hackathon.copilotdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "key", required = false) String key) {
        if (key == null || key.isEmpty()) {
            return "key not passed";
        }
        return "hello " + key;
    }
}
