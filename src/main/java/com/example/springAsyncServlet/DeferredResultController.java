package com.example.springAsyncServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class DeferredResultController {

    @Autowired
    CacheServices cacheServices;

    @GetMapping(value = "/deferredResult")
    public DeferredResult<String> rule() throws InterruptedException {

        DeferredResult<String> deferredResult = new DeferredResult<>();
        deferredResult.onCompletion(() -> {
            cacheServices.addValueToCache(Thread.currentThread().getId());
        });

        runSleepAndSetResultInDeferredResult(deferredResult);


        return deferredResult;
    }

    @Async
    void runSleepAndSetResultInDeferredResult(DeferredResult<String> deferredResult) throws InterruptedException {
        Thread.sleep(500);
        deferredResult.setResult("Done sleeping");
    }
}
