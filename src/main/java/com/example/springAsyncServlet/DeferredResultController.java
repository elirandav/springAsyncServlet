package com.example.springAsyncServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ForkJoinPool;

@RestController
public class DeferredResultController {

    @Autowired
    CacheServices cacheServices;

    @GetMapping(value = "/deferredResultWithService")
    public DeferredResult<String> rule() throws InterruptedException {

        DeferredResult<String> deferredResult = new DeferredResult<>();
        deferredResult.onCompletion(() -> {
            cacheServices.addValueToCache(Thread.currentThread().getId());
        });

        ForkJoinPool.commonPool().submit(()-> runSleepAndSetResultInDeferredResult(deferredResult));
        return deferredResult;
    }

    private void runSleepAndSetResultInDeferredResult(DeferredResult<String> deferredResult) {
        System.out.println("start processing " + Thread.currentThread().getId());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deferredResult.setResult("Done sleeping");
    }
}
