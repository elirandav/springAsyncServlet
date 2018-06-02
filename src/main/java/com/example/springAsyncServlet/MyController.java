package com.example.springAsyncServlet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

@RestController
public class MyController {

    @GetMapping(value = "/ruleTheWorld")
    public Callable<String> rule(){
        System.out.println("Start thread id: " + Thread.currentThread().getName());
        Callable<String> callable = () -> {
            String url = "http://localhost:8090/sleep/2000";
            new RestTemplate().getForObject(url, Boolean.TYPE);
            return "Ruling...";
        };

        return callable;
    }
}
