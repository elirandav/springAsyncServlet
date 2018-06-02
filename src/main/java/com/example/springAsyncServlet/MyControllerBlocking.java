package com.example.springAsyncServlet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyControllerBlocking {
    @GetMapping(value = "/ruleTheWorldBlocking")
    public String rule() {
        String url = "http://localhost:8090/sleep/2000";
        new RestTemplate().getForObject(url, Boolean.TYPE);
        return "Ruling blocking...";
    }
}
