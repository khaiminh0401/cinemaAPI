package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.MovieTestApplication;
import com.example.demo.config.GsonService;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest(classes = MovieTestApplication.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@AutoConfigureMockMvc
public class CustomerController {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GsonService gsonService;

	@DatabaseSetup(value = "/db/customer.xml")
	@ExpectedDatabase(value = "/expecteddb/customer.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
	@Test
	public void findById() throws Exception {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findById");
		this.mockMvc.perform(get("/api/customer/1")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.header("zuhot-key", "abc123456"))
				.andExpect(content().json(expect));
	}

	@DatabaseSetup(value = "/db/customer.xml")
	@ExpectedDatabase(value = "/expecteddb/confirm.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@Test
	public void registrationConfirm() throws Exception {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "registrationConfirm");
		this.mockMvc.perform(get("/api/customer/active").param("userToken", "12345678")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.header("zuhot-key", "abc123456"))
				.andExpect(content().json(expect));
	}
}
