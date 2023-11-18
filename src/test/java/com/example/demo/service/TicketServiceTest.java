package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.example.demo.MovieApplication;
import com.example.demo.config.GsonService;
import com.example.demo.entity.Ticket;
import com.example.demo.exception.InvalidRequestParameterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest(classes = MovieApplication.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
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
	public void testFindAll() throws JsonProcessingException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findAll");
		String result = objectMapper.writeValueAsString(ticketService.findAll());
		assertEquals(expect, result);
	}

	@Test
	public void testFindById() throws JsonProcessingException, InvalidRequestParameterException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findById");
		String result = objectMapper.writeValueAsString(ticketService.findById(120));
		assertEquals(expect, result);
	}

	@Test
	public void testFindByIdIsNull() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> ticketService.findById(null));
	}

	@Test
	public void testFindByIdIsNotPresent() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> ticketService.findById(-1));
	}

	@Test
	public void testFindByCustomerId() throws JsonProcessingException, InvalidRequestParameterException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findByCustomerId");
		String result = objectMapper.writeValueAsString(ticketService.findByCustomerId(Optional.of(1)));
		assertEquals(expect, result);
	}

	@Test
	public void testFindByCustomerIdIsNull() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> ticketService.findByCustomerId(null));
	}

	@Test
	public void testFindByCustomerIdIsNotPresent() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> ticketService.findByCustomerId(Optional.of(-1)));
	}

	@Test
	public void testFindByBillId() throws JsonProcessingException, InvalidRequestParameterException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findByBillId");
		String result = objectMapper.writeValueAsString(ticketService.findByBillId(Optional.of(1241)));
		assertEquals(expect, result);
	}

	@Test
	public void testFindByBillIdIsNull() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> ticketService.findByBillId(null));
	}

	@Test
	public void testFindByBillIdIsNotPresent() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> ticketService.findByBillId(Optional.of(-1)));
	}

	@DatabaseSetup(value = "/db/TicketServiceTest_testInsertTicketSuccess_db.xml")
	@ExpectedDatabase(value = "/expecteddb/TicketServiceTest_testInsertTicketSuccess_db_expect.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    @Test
	public void testInsertTicketSuccess() throws InvalidRequestParameterException {
		ticket = new Ticket();
		ticket.setId(1470);
		ticket.setCustomerId(1);
		ticket.setSeatDetailsId(1);
		ticket.setShowtimeId(1);
		ticket.setVat(0.05);
		ticket.setTotalPrice(10000);
		ticket.setBillId(1);
		ticketService.insert(Optional.of(ticket));
	}
}
