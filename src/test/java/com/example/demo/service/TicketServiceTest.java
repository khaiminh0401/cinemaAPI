package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.MovieApplication;
import com.example.demo.admin.controller.enums.RequestParameterEnum;
import com.example.demo.config.GsonService;
import com.example.demo.entity.Ticket;
import com.example.demo.exception.InvalidRequestParameterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = MovieApplication.class)
@AutoConfigureMockMvc
public class TicketServiceTest {
	@Autowired
    private TicketService ticketService;

	@Autowired
	private GsonService gsonService;

	@Autowired
	private ObjectMapper objectMapper;
	
	private Ticket ticket;

    @Test
    public void testFindAll() throws JsonProcessingException{
    	gsonService = new GsonService();
        String expect = gsonService.getValueExpect(this.getClass().toString(), "findAll");
        String result = objectMapper.writeValueAsString(ticketService.findAll());
        assertEquals(expect, result);
    }
    
    @Test
    public void testFindById() throws JsonProcessingException, InvalidRequestParameterException{
    	gsonService = new GsonService();
        String expect = gsonService.getValueExpect(this.getClass().toString(), "findById");
        String result = objectMapper.writeValueAsString(ticketService.findById(1));
        assertEquals(expect, result);
    }
    
    @Test
    public void testFindByIdIsNull() throws Exception {
        InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> ticketService.findById(null));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("Ticket", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOTHING.getName(), exception.getResponse().getParam());
    }
    
    @Test
    public void testFindByIdIsNotPresent() throws Exception {
        InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> ticketService.findById(-1));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("Ticket", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOT_FOUND.getName(), exception.getResponse().getParam());
    }
    
    @Test
    public void testFindByCustomerId() throws JsonProcessingException, InvalidRequestParameterException{
    	gsonService = new GsonService();
        String expect = gsonService.getValueExpect(this.getClass().toString(), "findByCustomerId");
        String result = objectMapper.writeValueAsString(ticketService.findByCustomerId(Optional.of(1)));
        assertEquals(expect, result);
    }
    
    @Test
    public void testFindByCustomerIdIsNull() throws Exception {
        InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> ticketService.findByCustomerId(null));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("Ticket", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOTHING.getName(), exception.getResponse().getParam());
    }
    
    @Test
    public void testFindByCustomerIdIsNotPresent() throws Exception {
        InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> ticketService.findByCustomerId(Optional.of(-1)));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("Ticket", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOT_FOUND.getName(), exception.getResponse().getParam());
    }
    
    @Test
    public void testFindByBillId() throws JsonProcessingException, InvalidRequestParameterException{
    	gsonService = new GsonService();
        String expect = gsonService.getValueExpect(this.getClass().toString(), "findByBillId");
        String result = objectMapper.writeValueAsString(ticketService.findByBillId(Optional.of(1)));
        assertEquals(expect, result);
    }
    
    @Test
    public void testFindByBillIdIsNull() throws Exception {
        InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> ticketService.findByBillId(null));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("Ticket", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOTHING.getName(), exception.getResponse().getParam());
    }
    
    @Test
    public void testFindByBillIdIsNotPresent() throws Exception {
        InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> ticketService.findByBillId(Optional.of(-1)));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("Ticket", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOT_FOUND.getName(), exception.getResponse().getParam());
    }
    
    @Test
	public void testInsert() throws Exception {
		ticket = new Ticket();
		ticket.setCustomerId(1);
		ticket.setSeatDetailsId(1);
		ticket.setShowtimeId(1);
		ticket.setVat(0.05);
		ticket.setTotalPrice(10000);
		ticket.setBillId(1);
		
		assertDoesNotThrow(() -> ticketService.insert(Optional.of(ticket)));
	}
    
    @Test
	public void testInsertWithThowError() throws Exception {
    	ticket = new Ticket();
		ticket.setCustomerId(1);
		ticket.setSeatDetailsId(1);
		ticket.setShowtimeId(1);
		ticket.setVat(0.05);
		ticket.setTotalPrice(10000);
		ticket.setBillId(1);
		
		assertThrows(InvalidRequestParameterException.class, () -> ticketService.insert(Optional.of(ticket)));
	}
}
