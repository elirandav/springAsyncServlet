package com.example.springAsyncServlet;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class CacheServices {
	public void addValueToCache(long id) {
		System.out.println("Adding value to cache " + id);
	}
}
