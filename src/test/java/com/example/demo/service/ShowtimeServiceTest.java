package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.demo.MovieApplication;
import com.example.demo.admin.controller.enums.RequestParameterEnum;
import com.example.demo.config.GsonService;
import com.example.demo.dao.ShowTimeDao;
import com.example.demo.entity.ShowTime;
import com.example.demo.exception.InvalidRequestParameterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest(classes = MovieApplication.class)
@AutoConfigureMockMvc
public class ShowtimeServiceTest {
	@Autowired
	private ShowTimeService showtimeService;

	@Autowired
	private GsonService gsonService;

	@Autowired
	private ObjectMapper objectMapper;
	
	private ShowTime showtime;

	@Test
	public void testFindShowtimeByMovieAndDate() throws JsonProcessingException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findShowtimeByMovieAndDate");
		String result = objectMapper.writeValueAsString(showtimeService.findShowtimeByMovieAndDate("2023-09-17", "MP53",
				Integer.valueOf(0), PageRequest.of(1, 1)));
		assertEquals(expect, result);
	}

	@Test
	public void testFindShowtimeByMovieAndDateIsNotPresent() throws JsonProcessingException {
		String expect = "\"Xin lỗi, không có xuất chiếu vào ngày này, hãy chọn một ngày khác.\"";
		String result = objectMapper.writeValueAsString(showtimeService.findShowtimeByMovieAndDate("2023-09-17", "-MP07",
					Integer.valueOf(-1), PageRequest.of(1, 1)));
		assertEquals(expect, result);
	}

	@Test
	public void testFindById() throws Exception {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findById");
		String result = objectMapper.writeValueAsString(showtimeService.findById(Optional.of(1)));
		assertEquals(expect, result);
	}

	@Test
	public void testFindByIdIsNotPresent() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> showtimeService.findById(Optional.of(-1)));
	}

	@Test
	public void testFindAll() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findAll");
		String result = mapper.writeValueAsString(showtimeService.findAll());
		assertEquals(expect, result);
	}

	@Test
	public void testFindByCurrentDate() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findByCurrentDate");
		String result = mapper.writeValueAsString(showtimeService.findByCurrentDate(Optional.of("cn1")));
		assertEquals(expect, result);
	}

	@Test
	public void testFindByCurrentDateIsNull() throws Exception {
		assertThrows(NullPointerException.class, () -> showtimeService.findByCurrentDate(null));
	}

	@Test
	public void testFindByCurrentDateIsNotPresent() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> showtimeService.findByCurrentDate(Optional.of("-CN01")));
	}

	@Test
	public void testFindByCurrentDateIsEmpty() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> showtimeService.findByCurrentDate(Optional.of("")));
	}

	@DatabaseSetup(value = "/db/ShowtimeServiceTest_testInsertShowtimeSuccess_db.xml")
	@ExpectedDatabase(value = "/expecteddb/ShowtimeServiceTest_testInsertShowtimeSuccess_db_expect.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    @Test
    @Order(1)
	public void testInsertShowtimeSuccess() throws InvalidRequestParameterException {
		showtime = new ShowTime();
		showtime.setId(0);
		showtime.setRoomId("PC35");
		showtime.setDimensionId(5);
		showtime.setPrice(50000.00000000000000000);
		showtime.setLanguageOfMovieId(12);
    	showtimeService.insert(showtime);
	}
	
	@DatabaseSetup(value = "/db/ShowtimeServiceTest_testUpdateShowtimeSuccess_db.xml")
	@ExpectedDatabase(value = "/expecteddb/ShowtimeServiceTest_testUpdateShowtimeSuccess_db_expect.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    @Test
    @Order(2)
	public void testUpdateShowtimeSuccess() throws InvalidRequestParameterException {
		showtime = new ShowTime();
		showtime.setId(97);
		showtime.setRoomId("PC35");
		showtime.setDimensionId(5);
		showtime.setPrice(70000.00000000000000000);
		showtime.setLanguageOfMovieId(12);
    	showtimeService.update(showtime);
	}
	
	@DatabaseSetup(value = "/db/ShowtimeServiceTest_testDeleteShowtimeSuccess_db.xml")
	@ExpectedDatabase(value = "/expecteddb/ShowtimeServiceTest_testDeleteShowtimeSuccess_db_expect.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    @Test
    @Order(3)
	public void testDeleteShowtimeSuccess() throws InvalidRequestParameterException {
    	showtimeService.delete(97);
	}
}
