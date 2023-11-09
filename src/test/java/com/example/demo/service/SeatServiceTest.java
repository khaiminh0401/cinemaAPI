package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.MovieApplication;
import com.example.demo.admin.controller.enums.RequestParameterEnum;
import com.example.demo.config.GsonService;
import com.example.demo.dao.SeatDao;
import com.example.demo.entity.Seat;
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
    	gsonService = new GsonService();
        String expect = gsonService.getValueExpect(this.getClass().toString(), "findByRoomId");
        System.err.println("expect" + expect);
        String result = objectMapper.writeValueAsString(seatService.findByRoomId("PC01"));
        System.err.println("result" + result);
        assertEquals(expect, result);
    }
    
    @Test
	public void findByRoomIdIsNull() throws Exception {
		Throwable exception = assertThrows(InvalidRequestParameterException.class, () -> {
			throw new InvalidRequestParameterException("Seat", RequestParameterEnum.NOT_FOUND);
		});
		
		try {
			seatService.findByRoomId(null);
		} catch (Exception e) {
			assertEquals(e.getMessage(), exception.getMessage());
		}
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
		Throwable exception = assertThrows(InvalidRequestParameterException.class, () -> {
			throw new InvalidRequestParameterException("Seat", RequestParameterEnum.NOT_FOUND);
		});
		
		try {
			seatService.getTotal(0, null);
		} catch (Exception e) {
			assertEquals(e.getMessage(), exception.getMessage());
		}
	}
    
    @Test
    public void getSeatHasCheckTicket() throws JsonProcessingException{
    	gsonService = new GsonService();
        String expect = gsonService.getValueExpect(this.getClass().toString(), "getSeatHasCheckTicket");
        String result = objectMapper.writeValueAsString(seatService.getSeatHasCheckTicket(1));
        assertEquals(expect, result);
    }
    
    @Test
	public void getSeatHasCheckTicketIsNull() throws Exception {
		Throwable exception = assertThrows(InvalidRequestParameterException.class, () -> {
			throw new InvalidRequestParameterException("Seat", RequestParameterEnum.NOT_FOUND);
		});
		
		try {
			seatService.getSeatHasCheckTicket(0);
		} catch (Exception e) {
			assertEquals(e.getMessage(), exception.getMessage());
		}
	}
}
