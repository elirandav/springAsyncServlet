package com.example.springAsyncServlet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

@RestController
public class MyCallableController {
    @GetMapping(value = "/ruleTheWorldAsync")
    public Callable<String> rule(){
        return () -> {
            String url = "http://localhost:8090/sleep/1000";
            new RestTemplate().getForObject(url, Boolean.TYPE);
            return "Ruling...";
        };
    }
}
