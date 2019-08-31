package com.example.springAsyncServlet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

@RestController
public class DeferredResultVsCompletableController {

    @GetMapping(value = "/deferredResult")
    public DeferredResult<Boolean> useDeferredResult() {
        System.out.println("Got request " + threadName());
        DeferredResult<Boolean> deferredResult = new DeferredResult<>();
        deferredResult.onCompletion(() -> System.out.println("log result... " + threadName()));
        ForkJoinPool.commonPool().submit(() -> {
            deferredResult.setResult(processRequest());
        });
        System.out.println("Return deferredResult " + threadName());
        return deferredResult;
    }

    @GetMapping(value = "/completableFuture")
    public CompletableFuture<Boolean> useCompletableFuture() {
        System.out.println("Got request " + threadName());
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(this::processRequest)
                .thenApplyAsync(this::logResult);
        System.out.println("Return completableFuture " + threadName());
        return completableFuture;
    }

    private boolean logResult(Boolean result) {
        System.out.println("Result: " + result + " " + threadName());
        return true;
    }

    private boolean processRequest() {
        System.out.println("Start processing " + threadName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    private String threadName() {
        return Thread.currentThread().getName();
    }

}
