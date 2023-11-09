package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThatException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.example.demo.MovieTestApplication;
import com.example.demo.admin.controller.enums.RequestParameterEnum;
import com.example.demo.config.GsonService;
import com.example.demo.exception.InvalidRequestParameterException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
public class MovieServiceTest {

	@Autowired
	private GsonService gsonService;

	@Autowired
	private MovieService movieService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void findAll() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findAll");
		String result = objectMapper.writeValueAsString(movieService.findAll());
		assertEquals(expect, result);
	}

	@Test
	public void findById() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findById");
		String result = objectMapper.writeValueAsString(movieService.findById("MP01"));
		assertEquals(expect, result);
	}

	@Test
	public void findByIdIsNull() throws Exception {
		Throwable exception = assertThrows(InvalidRequestParameterException.class, () -> {
			throw new InvalidRequestParameterException("Phim", RequestParameterEnum.NOT_FOUND);
		});
		try {
			movieService.findById(null);
		} catch (Exception e) {
			assertEquals(e.getMessage(), exception.getMessage());
		}
	}
	
	@Test
	public void findMovieDetailPage() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findMovieDetailPage");
		String result = objectMapper.writeValueAsString(movieService.findMovieDetailPage("MP01"));
		assertEquals(expect, result);
	}

	@Test
	public void findAllMovieAdmin() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findAllMovieAdmin");
		String result = objectMapper.writeValueAsString(movieService.findAllMovieAdmin());
		assertEquals(expect, result);
	}

	@Test
	public void getByShowTime() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "getByShowTime");
		String result = objectMapper.writeValueAsString(movieService.findByShowTimeId(1));
		assertEquals(expect, result);
	}

	@Test
	public void getByBill() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "getByBill");
		String result = objectMapper.writeValueAsString(movieService.getByBill(10));
		assertEquals(expect, result);
	}
}
