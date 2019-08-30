package com.example.springAsyncServlet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ForkJoinPool;

@RestController
public class DeferredResultWithForkjoinController {

    @GetMapping(value = "/deferredResultError")
    public DeferredResult<ResponseEntity<String>> getDeferredResultsError() {
        DeferredResult<ResponseEntity<String>> deferredResult = new DeferredResult<>();
        ForkJoinPool.commonPool().submit(() -> {
            System.out.println("Processing...");
            int age = 0;
            try {
                age = age / 0;
                deferredResult.setResult(ResponseEntity.ok("completed"));
            }catch(Exception e) {
                System.out.println("Failed to process: " + e.getMessage());
                deferredResult.setErrorResult(
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(e.getMessage()));
            }
        });

        return deferredResult;
    }
}
