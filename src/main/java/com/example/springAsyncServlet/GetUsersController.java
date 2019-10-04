package com.example.springAsyncServlet;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class GetUsersController {

    Logger logger = LoggerFactory.getLogger(GetUsersController.class);
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "/users/{userId}")
    public User getUsers(@PathVariable String userId) {
        logger.info("Getting sequentially the user object of: " + userId);
        String name =  getName(userId);
        String phoneNumber = getPhoneNumber(userId);
        return new User(userId, name, phoneNumber);
    }


    @GetMapping(value = "/users_parallel/{userId}")
    public User getUsersParallel(@PathVariable String userId) throws ExecutionException, InterruptedException {
        UUID idToTrackBottleneck = UUID.randomUUID();
        String userIdWithUUID = userId + "_" +idToTrackBottleneck;
        logger.info("Got request: " + userIdWithUUID);

        CompletableFuture<String> name = CompletableFuture
                .supplyAsync(()-> getName(userIdWithUUID));
        CompletableFuture<String> phoneNumber = CompletableFuture
                .supplyAsync(()-> getPhoneNumber(userIdWithUUID));
        return new User(userId, name.get(), phoneNumber.get());
    }

    private String getName(String userId) {
        logger.info("Calling metadata service to get the name of user: " + userId);
        String url = "http://localhost:8090/metadata/names/" + userId;
        return restTemplate.getForObject(url, String.class);
    }

    private String getPhoneNumber(String userId) {
        logger.info("Calling metadata service to get the phone number of user: " + userId);
        String url = "http://localhost:8090/metadata/phoneNumbers/" + userId;
        return restTemplate.getForObject(url, String.class);
    }

    @Data
    private class User {
        private String id;
        private String name;
        private String phoneNumber;

        public User() {
        }

        public User(String id, String name, String phoneNumber) {
            this.id = id;
            this.name = name;
            this.phoneNumber = phoneNumber;
        }
    }
}
