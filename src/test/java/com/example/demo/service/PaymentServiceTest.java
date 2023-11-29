package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.example.demo.MovieTestApplication;
import com.example.demo.config.GsonService;
import com.example.demo.entity.PaymentDetails;
import com.example.demo.exception.InvalidRequestParameterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@TestMethodOrder(OrderAnnotation.class)
public class PaymentServiceTest {
	@Autowired
	private PaymentService paymentService;

	@Autowired
	private GsonService gsonService;

	@Autowired
	private ObjectMapper objectMapper;

	@Order(1)
	@DatabaseSetup(value = "/db/PaymentServiceTest_testInsertPaymentMethodDetailsSucces_db.xml")
	@ExpectedDatabase(value = "/expecteddb/PaymentServiceTest_testInsertPaymentMethodDetailsSucces_db_expect.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@Test
	public void insertPaymentDetailsSucces() throws Exception {
		PaymentDetails data = new PaymentDetails("PM2", "nv01", 10, 0, "12345", 10000);
		assertDoesNotThrow(() -> paymentService.insertPaymentDetails(data));
	}

	@Order(2)
	@DatabaseSetup(value = "/expecteddb/PaymentServiceTest_testInsertPaymentMethodDetailsSucces_db_expect.xml")
	@ExpectedDatabase(value = "/expecteddb/PaymentServiceTest_testUpdatePaymentMethodDetailsSucces_db_expect.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
	@Test
	public void updateStatusPaymentDetailsSucces() throws Exception {
		PaymentDetails data = new PaymentDetails();
		data.setId(8);
		data.setStatus(1);
		assertDoesNotThrow(() -> paymentService.updateStatusPaymentDetails(data));
	}

	@Test
	@Order(3)
	public void findByTransactionNo() throws InvalidRequestParameterException, JsonProcessingException {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findByTransactionNo");
		String actual = gsonService.exportAndGetActual(this.getClass().toString(), "findByTransactionNo",
				paymentService.findByTransactionNo(Optional.of("12345")));
		assertEquals(expect, actual);
	}

}
