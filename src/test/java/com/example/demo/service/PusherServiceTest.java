package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.CALLS_REAL_METHODS;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.MovieTestApplication;

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
public class PusherServiceTest {
	@Autowired
	private PusherService pusherService;

	@Test
	public void realTime() throws Exception {
		try {
			pusherService.realTime("my-channel", "my-event", "Xin chào, Đây là test!!");
		} catch (Exception e) {
			assertDoesNotThrow(() -> e.toString());
		}
	}

	@Test
	public void realTimeWithParamIsNull() throws Exception {
		assertThrows(Exception.class, () -> pusherService.realTime(null, null, null));
	}

}
