package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.MovieTestApplication;
import com.example.demo.config.GsonService;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;


import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(classes = MovieTestApplication.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@AutoConfigureMockMvc
public class CustomerController {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private GsonService gsonService;

    @DatabaseSetup(value="/db/customer.xml")
    @Test
    public void findById() throws Exception{
		String expect = gsonService.getValueExpect(this.getClass().toString(),"findById");
		System.out.println(expect);
		this.mockMvc.perform(get("/api/customer/1")
							.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                            .header("zuhot-key", "abc123435")
                        )
					.andExpect(content().string(containsString(expect)));
    }
}
