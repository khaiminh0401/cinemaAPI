package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.MovieTestApplication;
import com.example.demo.config.GsonService;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
public class HomeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GsonService gsonService;
	
	@Test
	public void getAll() throws Exception {
		String expect = gsonService.getValueExpect(this.getClass().toString(),"getAll");
		System.out.println(expect);
		this.mockMvc.perform(get("/api/home/getData")
							.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
					.andExpect(content().string(containsString(expect.toString())));
	}
}
