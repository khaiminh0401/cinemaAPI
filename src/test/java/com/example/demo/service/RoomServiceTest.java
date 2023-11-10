package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
public class RoomServiceTest {
	@Autowired
	private RoomService roomService;

	@Autowired
	private GsonService gsonService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void findAll() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findAll");
		String result = objectMapper.writeValueAsString(roomService.findAll());
		assertEquals(expect, result);
	}

	@Test
	public void findById() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findById");
		String result = objectMapper.writeValueAsString(roomService.findById("PC01"));
		assertEquals(expect, result);
	}

	@Test
	public void findByIdIsNull() throws Exception {
		try {
			roomService.findById(null);
		} catch (Exception e) {
			assertThrows(InvalidRequestParameterException.class,
					() -> new InvalidRequestParameterException("Room", RequestParameterEnum.NOT_FOUND));
		}
	}

	@Test
	public void findByIdIsEmpty() throws Exception {
		try {
			roomService.findById("");
		} catch (Exception e) {
			assertThrows(InvalidRequestParameterException.class,
					() -> new InvalidRequestParameterException("Room", RequestParameterEnum.NOT_FOUND));
		}
	}

	@Test
	public void getByBranch() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "getByBranch");
		String result = objectMapper.writeValueAsString(roomService.getByBranch("cn1", "27/09/2023"));
		assertEquals(expect, result);
	}

	@Test
	public void getByBranchIsNotFound() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> roomService.getByBranch("cn1", "21/09/2023"));
	}
}
