package com.example.demo.controller;

import com.example.demo.MovieTestApplication;
import com.example.demo.config.GsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
public class MovieControllerTest {
	@Autowired
	private MockMvc mockMvc;

	// @Autowired
	// private GsonService gsonService;

	@Test
	public void findById() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findById");
		System.out.println(expect);
		this.mockMvc.perform(
				get("/api/movie/MP01").accept(MediaType.APPLICATION_JSON_UTF8_VALUE).header("zuhot-key", "abc123435"))
				.andExpect(content().string(containsString(expect.toString())));
	}

	@Test
	public void findAll() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findAll");
		this.mockMvc
				.perform(
						get("/api/movie").accept(MediaType.APPLICATION_JSON_UTF8_VALUE).header("zuhot-key", "abc12345"))
				.andExpect(content().string(containsString(expect.toString())));
	}

	@Test
	public void findMovieDetailPage() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findMovieDetailPage");
		this.mockMvc
				.perform(get("/api/movie/getDetail?id=MP01").accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.header("zuhot-key", "abc12345"))
				.andExpect(content().string(containsString(expect.toString())));
	}

	@Test
	public void findAllMovieAdmin() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findAllMovieAdmin");
		this.mockMvc
				.perform(get("/api/movie/findAllMovieAdmin").accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.header("zuhot-key", "abc12345"))
				.andExpect(content().string(containsString(expect.toString())));
	}

	@Test
	public void getByShowTime() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "getByShowTime");
		this.mockMvc
				.perform(get("/api/movie/getByShowTime?showtimeid=1").accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.header("zuhot-key", "abc12345"))
				.andExpect(content().string(containsString(expect.toString())));
	}

	@Test
	public void getByBill() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "getByBill");
		this.mockMvc
				.perform(get("/api/movie/getByBill?id=10").accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.header("zuhot-key", "abc12345"))
				.andExpect(content().string(containsString(expect.toString())));
	}
}
