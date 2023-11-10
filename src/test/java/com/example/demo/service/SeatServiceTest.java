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
    public void findByRoomId() throws JsonProcessingException{
        String expect = gsonService.getValueExpect(this.getClass().toString(), "findByRoomId");
        String result = objectMapper.writeValueAsString(seatService.findByRoomId("PC01"));
        assertEquals(expect, result);
    }
    
    @Test
    public void findByRoomIdIsNull() throws Exception {
        InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> seatService.findByRoomId(null));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("Seat", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOTHING.getName(), exception.getResponse().getParam());
    }
    
    @Test
    public void findByRoomIdIsNotPresent() throws Exception {
        InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> seatService.findByRoomId("-PC01"));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("Seat", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOT_FOUND.getName(), exception.getResponse().getParam());
    }
    
    @Test
    public void findByRoomIdIsEmpty() throws Exception {
        InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> seatService.findByRoomId(""));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("Seat", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOTHING.getName(), exception.getResponse().getParam());
    }    
      
    @Test
    public void getTotal() throws JsonProcessingException{
    	gsonService = new GsonService();
        String expect = gsonService.getValueExpect(this.getClass().toString(), "getTotal");
        String result = objectMapper.writeValueAsString(seatService.getTotal(1, "A1"));
        assertEquals(expect, result);
    }
    
    @Test
    public void getTotalIsNull() throws Exception {
        InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> seatService.getTotal(0, null));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("Seat", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOTHING.getName(), exception.getResponse().getParam());
    }
    
    @Test
    public void getTotalIsNotPresent() throws Exception {
        InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> seatService.getTotal(-1, ""));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("Seat", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOTHING.getName(), exception.getResponse().getParam());
    }
    
    @Test
    public void getSeatHasCheckTicket() throws JsonProcessingException{
        String expect = gsonService.getValueExpect(this.getClass().toString(), "getSeatHasCheckTicket");
        String result = objectMapper.writeValueAsString(seatService.getSeatHasCheckTicket(1));
        assertEquals(expect, result);
    }
    
    @Test
    public void getSeatHasCheckTicketIsNull() throws Exception {
        InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> seatService.getSeatHasCheckTicket(0));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("Seat", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOTHING.getName(), exception.getResponse().getParam());
    }
    
    @Test
    public void getSeatHasCheckTicketIsNotPresent() throws Exception {
        InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> seatService.getSeatHasCheckTicket(-1));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("Seat", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOT_FOUND.getName(), exception.getResponse().getParam());
    }
}
