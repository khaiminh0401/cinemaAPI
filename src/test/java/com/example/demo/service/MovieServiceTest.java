package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.example.demo.MovieTestApplication;
import com.example.demo.config.GsonService;
import com.example.demo.dao.MovieDao;
import com.example.demo.dto.requestMovieDto;
import com.example.demo.exception.InvalidRequestParameterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

/**
 * @author thanhson
 *
 */
@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
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
		assertThrows(InvalidRequestParameterException.class, () -> movieService.findMovieById(""));
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

	@DatabaseSetup(value = "/db/MovieServiceTest_testInsertMovieSuccess_db.xml")
	@ExpectedDatabase(value = "/expecteddb/MovieServiceTest_testInsertMovieSuccess_db_expect.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@Test
	public void insertMovieSucces() throws Exception {
		requestMovieDto movieDto = new requestMovieDto();
		movieDto.setId("MP56");
		movieDto.setName("Alibaba");
		movieDto.setYearofmanufacture(2023);
		movieDto.setCountryid(20);
		movieDto.setTime(105);
		movieDto.setDescribe("test demo");
		movieDto.setTrailer("test");
		movieDto.setStatus("1");
		movieDto.setPoster("MP56.jpg");
		movieDto.setArrayLanguage(new ArrayList<Integer>(List.of(8, 16)));
		movieDto.setArrayType(new ArrayList<String>(List.of("LP01")));
		movieDto.setLimitage(16);
		movieDto.setArrayActor(new ArrayList<Integer>(List.of(1, 2)));
		movieDto.setArrayDirector(new ArrayList<Integer>(List.of(1)));
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\movie\\MP56.jpg");
		MockMultipartFile multipartFile = new MockMultipartFile("MP56", "MP56.jpg", "image/jpg", fis);
		assertDoesNotThrow(() -> movieService.insertMovie(movieDto, multipartFile));
	}
	
	@DatabaseSetup(value = "/expecteddb/MovieServiceTest_testInsertMovieSuccess_db_expect.xml")
	@ExpectedDatabase(value = "/expecteddb/MovieServiceTest_testUpdateMovieSuccess_db_expect.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@Test
	public void updateMovieSucces() throws Exception {
		requestMovieDto movieDto = new requestMovieDto();
		movieDto.setId("MP56");
		movieDto.setName("Alibaba Kun");
		movieDto.setDescribe("test demo 123");
		movieDto.setYearofmanufacture(2023);
		movieDto.setTrailer("test");
		movieDto.setTime(105);
		movieDto.setStatus("1");
		movieDto.setCountryid(20);
		movieDto.setLimitage(16);
		movieDto.setArrayLanguage(new ArrayList<Integer>(List.of(8, 16)));
		movieDto.setArrayType(new ArrayList<String>(List.of("LP01")));
		movieDto.setArrayActor(new ArrayList<Integer>(List.of(1, 2)));
		movieDto.setArrayDirector(new ArrayList<Integer>(List.of(1)));
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\movie\\MP56.jpg");
		MockMultipartFile multipartFile = new MockMultipartFile("MP56", "MP56.jpg", "image/jpg", fis);
		assertDoesNotThrow(() -> movieService.updateMovie(movieDto, multipartFile));
	}
}
