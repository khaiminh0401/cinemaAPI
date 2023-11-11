package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.MovieTestApplication;
import com.example.demo.config.GsonService;
import com.example.demo.exception.InvalidRequestParameterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author thanhson
 *
 */
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
	public void findAll() throws InvalidRequestParameterException, JsonProcessingException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findAll");
		String result = objectMapper.writeValueAsString(movieService.findAll());
		assertEquals(expect, result);
	}

	@Test
	public void findById() throws InvalidRequestParameterException, JsonProcessingException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findById");
		String result = objectMapper.writeValueAsString(movieService.findById("MP01"));
		assertEquals(expect, result);
	}

	@Test
	public void findByIdIsNotFound() throws InvalidRequestParameterException {
		assertThrows(InvalidRequestParameterException.class, () -> movieService.findById("123"));
	}

	@Test
	public void findByIdIsNull() throws InvalidRequestParameterException {
		assertThrows(InvalidRequestParameterException.class, () -> movieService.findById(null));
	}

	@Test
	public void findByIdIsEmpty() throws InvalidRequestParameterException {
		assertThrows(InvalidRequestParameterException.class, () -> movieService.findById(""));
	}

	@Test
	public void findByStatus() throws InvalidRequestParameterException, JsonProcessingException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findByStatus");
		String result = objectMapper.writeValueAsString(movieService.findByStatus("1"));
		assertEquals(expect, result);
	}

	@Test
	public void findByStatusIsNotFound() throws InvalidRequestParameterException {
		assertThrows(InvalidRequestParameterException.class, () -> movieService.findByStatus("5"));
	}

	@Test
	public void findByStatusIsNull() throws InvalidRequestParameterException {
		assertThrows(InvalidRequestParameterException.class, () -> movieService.findByStatus(null));
	}

	@Test
	public void findByStatusIsEmpty() throws InvalidRequestParameterException {
		assertThrows(InvalidRequestParameterException.class, () -> movieService.findByStatus(""));
	}

//	@Test
//	public void findMoviesNowShowing() throws InvalidRequestParameterException {
//		GsonService gsonService = new GsonService();
//		String expect = gsonService.getValueExpect(this.getClass().toString(), "findMoviesNowShowing");
//		String result = objectMapper.writeValueAsString(movieService.findMoviesNowShowing());
//		assertEquals(null, result);
//	}

	@Test
	public void findMovieDetailPageWithMovieId() throws InvalidRequestParameterException, JsonProcessingException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findMovieDetailPage");
		String result = objectMapper.writeValueAsString(movieService.findMovieDetailPage(Optional.ofNullable("MP07")));
		assertEquals(expect, result);
	}

	@Test
	public void findMovieDetailPageWithMovieIdIsNull() throws InvalidRequestParameterException {
		assertThrows(InvalidRequestParameterException.class, () -> movieService.findMovieDetailPage(null));
	}

	@Test
	public void findMovieHomePage() throws InvalidRequestParameterException, JsonProcessingException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findMovieHomePage");
		String result = objectMapper.writeValueAsString(movieService.findMovieHomePage("cn1", 1, "LP06", "1"));
		assertEquals(expect, result);
	}

	@Test
	public void findMovieHomePageIsNotFound() throws InvalidRequestParameterException {
		assertThrows(InvalidRequestParameterException.class,
				() -> movieService.findMovieHomePage("cn1", 12, "LP06", "1"));
	}

	@Test
	public void findByName() throws InvalidRequestParameterException, JsonProcessingException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findByName");
		String result = objectMapper.writeValueAsString(movieService.findByName("Ác quỷ"));
		assertEquals(expect, result);
	}

	@Test
	public void findByNameIsNotFound() throws InvalidRequestParameterException {
		assertThrows(InvalidRequestParameterException.class, () -> movieService.findByName("Hài"));
	}

	@Test
	public void findByNameIsEmpty() throws InvalidRequestParameterException, JsonProcessingException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findAll");
		String result = objectMapper.writeValueAsString(movieService.findByName(""));
		assertEquals(expect, result);
	}

	@Test
	public void findAllMovieAdmin() throws InvalidRequestParameterException, JsonProcessingException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findAllMovieAdmin");
		String result = objectMapper.writeValueAsString(movieService.findAllMovieAdmin());
		assertEquals(expect, result);
	}

	@Test
	public void findMovieById() throws InvalidRequestParameterException, JsonProcessingException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findMovieById");
		String result = objectMapper.writeValueAsString(movieService.findMovieById("MP01"));
		assertEquals(expect, result);
	}

	@Test
	public void findMovieByIdIsNull() throws InvalidRequestParameterException {
		assertThrows(InvalidRequestParameterException.class, () -> movieService.findMovieById(null));
	}

	@Test
	public void findMovieByIdIsEmpty() throws InvalidRequestParameterException {
	}

	@Test
	public void getByShowTime() throws InvalidRequestParameterException, JsonProcessingException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "getByShowTime");
		String result = objectMapper.writeValueAsString(movieService.findByShowTimeId(1));
		assertEquals(expect, result);
	}

	@Test
	public void getByBill() throws InvalidRequestParameterException, JsonProcessingException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "getByBill");
		String result = objectMapper.writeValueAsString(movieService.getByBill(Optional.ofNullable(10)));
		assertEquals(expect, result);
	}

	@Test
	public void getByBillIsNull() throws InvalidRequestParameterException, JsonProcessingException {
		assertThrows(InvalidRequestParameterException.class, () -> movieService.getByBill(null));
	}
}
