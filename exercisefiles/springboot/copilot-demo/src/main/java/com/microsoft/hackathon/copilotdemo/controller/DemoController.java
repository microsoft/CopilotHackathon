package com.microsoft.hackathon.copilotdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/* 
* Create a GET operation to return the value of a key passed as query parameter. 
* If the key is not passed, return "key not passed".
* If the key is passed, return "hello <key>".
* 
*/
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
