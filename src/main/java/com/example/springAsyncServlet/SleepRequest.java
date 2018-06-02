package com.example.springAsyncServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicInteger;

public class SleepRequest {
    static Logger logger = LoggerFactory.getLogger(SleepRequest.class);
    private AtomicInteger requestCounter = new AtomicInteger(1);
    public SleepRequest() {
    }
    private static final long  MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE ;
    }

}