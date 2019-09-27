package com.example.springAsyncServlet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class GetUsersController {

    @GetMapping(value = "/users/{ids}")
    public List<User> getUsers(@PathVariable String[] ids) {
        return Stream.of(ids).map(id -> new User(id, getPhoneNumber(id)))
                .collect(Collectors.toList());
    }


    private String getPhoneNumber(String id) {
        String url = "http://localhost:8090/phoneNumbers/" + id;
        return new RestTemplate().getForObject(url, String.class);
    }

    private class User {
        private String id;
        private String phoneNumber;

        public User() {
        }

        public User(String id, String phoneNumber) {
            this.id = id;
            this.phoneNumber = phoneNumber;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }
}
