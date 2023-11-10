package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.MovieTestApplication;
import com.example.demo.admin.controller.enums.RequestParameterEnum;
import com.example.demo.config.GsonService;
import com.example.demo.exception.InvalidRequestParameterException;
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
	public void findByIdIsEmpty() throws Exception {
		Throwable exception = assertThrows(InvalidRequestParameterException.class, () -> {
			throw new InvalidRequestParameterException("Phim", RequestParameterEnum.NOT_FOUND);
		});
		try {
			movieService.findById("");
		} catch (Exception e) {
			assertEquals(e.getMessage(), exception.getMessage());
		}
	}

	@Test
	public void findByStatus() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findByStatus");
		String result = objectMapper.writeValueAsString(movieService.findByStatus("1"));
		assertEquals(expect, result);
	}

	@Test
	public void findByStatusIsNull() throws Exception {
		Throwable exception = assertThrows(InvalidRequestParameterException.class, () -> {
			throw new InvalidRequestParameterException("Phim", RequestParameterEnum.NOT_FOUND);
		});
		try {
			movieService.findByStatus(null);
		} catch (Exception e) {
			assertEquals(e.getMessage(), exception.getMessage());
		}
	}
	
	@Test
	public void findByStatusIsEmpty() throws Exception {
		Throwable exception = assertThrows(InvalidRequestParameterException.class, () -> {
			throw new InvalidRequestParameterException("Phim", RequestParameterEnum.NOT_FOUND);
		});
		try {
			movieService.findByStatus("");
		} catch (Exception e) {
			assertEquals(e.getMessage(), exception.getMessage());
		}
	}

//	@Test
//	public void findMoviesNowShowing() throws Exception {
//		GsonService gsonService = new GsonService();
//		String expect = gsonService.getValueExpect(this.getClass().toString(), "findMoviesNowShowing");
//		String result = objectMapper.writeValueAsString(movieService.findMoviesNowShowing());
//		assertEquals(null, result);
//	}

	@Test
	public void findMovieDetailPageWithMovieId() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findMovieDetailPage");
		String result = objectMapper.writeValueAsString(movieService.findMovieDetailPage("MP07"));
		assertEquals(expect, result);
	}

	@Test
	public void findMovieDetailPageWithMovieIdIsNull() throws Exception {
		Throwable exception = assertThrows(InvalidRequestParameterException.class, () -> {
			throw new InvalidRequestParameterException("Phim", RequestParameterEnum.NOT_FOUND);
		});
		try {
			movieService.findMovieDetailPage(null);
		} catch (Exception e) {
			assertFalse(e.getMessage().equals(exception.getMessage()));
		}
	}

	@Test
	public void findMovieHomePage() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findMovieHomePage");
		String result = objectMapper.writeValueAsString(movieService.findMovieHomePage("cn1", 1, "LP06", "1"));
		assertEquals(expect, result);
	}

	@Test
	public void findMovieHomePageIsNotFound() throws Exception {
		Throwable exception = assertThrows(InvalidRequestParameterException.class, () -> {
			throw new InvalidRequestParameterException("Phim", RequestParameterEnum.NOT_FOUND);
		});
		try {
			movieService.findMovieHomePage("cn1", 12, "LP06", "1");
		} catch (Exception e) {
			assertEquals(e.getMessage(), exception.getMessage());
		}
	}

	@Test
	public void findByName() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findByName");
		String result = objectMapper.writeValueAsString(movieService.findByName("Ác quỷ"));
		assertEquals(expect, result);
	}

	@Test
	public void findByNameIsNotFound() throws Exception {
		Throwable exception = assertThrows(InvalidRequestParameterException.class, () -> {
			throw new InvalidRequestParameterException("Phim", RequestParameterEnum.NOT_FOUND);
		});
		try {
			movieService.findByName("Hài");
		} catch (Exception e) {
			assertEquals(e.getMessage(), exception.getMessage());
		}
	}

	@Test
	public void findByNameIsEmpty() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findAll");
		String result = objectMapper.writeValueAsString(movieService.findByName(""));
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
	public void findMovieById() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findMovieById");
		String result = objectMapper.writeValueAsString(movieService.findMovieById("MP01"));
		assertEquals(expect, result);
	}
	
	@Test
	public void findMovieByIdIsNull() throws Exception {
		Throwable exception = assertThrows(InvalidRequestParameterException.class, () -> {
			throw new InvalidRequestParameterException("Phim", RequestParameterEnum.NOT_FOUND);
		});
		try {
			movieService.findMovieById(null);
		} catch (Exception e) {
			assertEquals(e.getMessage(), exception.getMessage());
		}
	}
	
	@Test
	public void findMovieByIdIsEmpty() throws Exception {
		Throwable exception = assertThrows(InvalidRequestParameterException.class, () -> {
			throw new InvalidRequestParameterException("Phim", RequestParameterEnum.NOT_FOUND);
		});
		try {
			movieService.findMovieById("");
		} catch (Exception e) {
			assertEquals(e.getMessage(), exception.getMessage());
		}
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
