package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.MovieApplication;
import com.example.demo.MovieTestApplication;
import com.example.demo.admin.controller.enums.RequestParameterEnum;
import com.example.demo.config.GsonService;
import com.example.demo.exception.InvalidRequestParameterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
public class SeatServiceTest {
	@Autowired
	private SeatService seatService;

	@Autowired
	private GsonService gsonService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testFindByRoomId() throws JsonProcessingException, InvalidRequestParameterException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findByRoomId");
		String result = objectMapper.writeValueAsString(seatService.findByRoomId(Optional.of("PC01")));
		assertEquals(expect, result);
	}

	@Test
	public void testFindByRoomIdIsNull() throws Exception {
		assertThrows(NullPointerException.class, () -> seatService.findByRoomId(null));
	}

	@Test
	public void testFindByRoomIdIsNotPresent() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> seatService.findByRoomId(Optional.of("-PC01")));
	}

	@Test
	public void testFindByRoomIdIsEmpty() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> seatService.findByRoomId(Optional.of("")));
	}

	@Test
	public void testGetTotal() throws JsonProcessingException, InvalidRequestParameterException {
		gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "getTotal");
		String result = objectMapper.writeValueAsString(seatService.getTotal(Optional.of(1), Optional.of("A1")));
		assertEquals(expect, result);
	}

	@Test
	public void testGetTotalIsNull() throws Exception {
		assertThrows(NullPointerException.class, () -> seatService.getTotal(Optional.of(0), null));
	}

	@Test
	public void testGetTotalIsNotPresent() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> seatService.getTotal(Optional.of(-1), Optional.of("")));
	}

	@Test
	public void testGetSeatHasCheckTicket() throws JsonProcessingException, InvalidRequestParameterException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "getSeatHasCheckTicket");
		String result = objectMapper.writeValueAsString(seatService.getSeatHasCheckTicket(Optional.of(1)));
		assertEquals(expect, result);
	}

	@Test
	public void testGetSeatHasCheckTicketIsNull() throws Exception {
		assertThrows(NullPointerException.class, () -> seatService.getSeatHasCheckTicket(null));
	}

	@Test
	public void testGetSeatHasCheckTicketIsNotPresent() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> seatService.getSeatHasCheckTicket(Optional.of(-1)));
	}
}
