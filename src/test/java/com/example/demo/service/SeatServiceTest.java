package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.MovieApplication;
import com.example.demo.admin.controller.enums.RequestParameterEnum;
import com.example.demo.config.GsonService;
import com.example.demo.exception.InvalidRequestParameterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = MovieApplication.class)
@AutoConfigureMockMvc
public class SeatServiceTest {
	@Autowired
	private SeatService seatService;

	@Autowired
	private GsonService gsonService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testFindByRoomId() throws JsonProcessingException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findByRoomId");
		String result = objectMapper.writeValueAsString(seatService.findByRoomId("PC01"));
		assertEquals(expect, result);
	}

	@Test
	public void testFindByRoomIdIsNull() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> seatService.findByRoomId(null));
	}

	@Test
	public void testFindByRoomIdIsNotPresent() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> seatService.findByRoomId("-PC01"));
	}

	@Test
	public void testFindByRoomIdIsEmpty() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> seatService.findByRoomId(""));
	}

	@Test
	public void testGetTotal() throws JsonProcessingException {
		gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "getTotal");
		String result = objectMapper.writeValueAsString(seatService.getTotal(1, "A1"));
		assertEquals(expect, result);
	}

	@Test
	public void testGetTotalIsNull() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> seatService.getTotal(0, null));
	}

	@Test
	public void testGetTotalIsNotPresent() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> seatService.getTotal(-1, ""));
	}

	@Test
	public void testGetSeatHasCheckTicket() throws JsonProcessingException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "getSeatHasCheckTicket");
		String result = objectMapper.writeValueAsString(seatService.getSeatHasCheckTicket(1));
		assertEquals(expect, result);
	}

	@Test
	public void testGetSeatHasCheckTicketIsNull() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> seatService.getSeatHasCheckTicket(0));
	}

	@Test
	public void testGetSeatHasCheckTicketIsNotPresent() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> seatService.getSeatHasCheckTicket(-1));
	}
}
