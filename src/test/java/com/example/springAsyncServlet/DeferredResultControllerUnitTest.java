package com.example.springAsyncServlet;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.request.async.DeferredResult;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(DeferredResultController.class)
public class DeferredResultControllerUnitTest {

	@MockBean
	CacheServices cacheServices;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void onCompletionTest() throws Exception {
		mockMvc.perform(get("/deferredResult"))
				.andDo(mvcResult -> mvcResult.getRequest().getAsyncContext().complete());
		Mockito.verify(cacheServices).addValueToCache(Thread.currentThread().getId());
	}
}
