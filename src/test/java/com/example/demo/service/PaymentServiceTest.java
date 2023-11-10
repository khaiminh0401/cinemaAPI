package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.MovieTestApplication;
import com.example.demo.admin.controller.enums.RequestParameterEnum;
import com.example.demo.config.GsonService;
import com.example.demo.entity.PaymentDetails;
import com.example.demo.exception.InvalidRequestParameterException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
public class PaymentServiceTest {
	@Autowired
	private PaymentService paymentService;

	@Autowired
	private GsonService gsonService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void insertPaymentDetails() throws Exception {
		PaymentDetails data = new PaymentDetails("PM1", "nv01", 10, 0, null, 10000);
		assertDoesNotThrow(() -> paymentService.insertPaymentDetails(data));
	}

	@Test
	public void insertPaymentDetailsWithThowError() throws Exception {
		PaymentDetails data = new PaymentDetails("PM6", "nv01", 12, 0, null, 10000);
		assertThrows(InvalidRequestParameterException.class, () -> paymentService.insertPaymentDetails(data));
	}

	@Test
	public void updateStatusPaymentDetails() throws Exception {
		PaymentDetails data = new PaymentDetails();
		data.setId(8);
		data.setStatus(0);
		assertDoesNotThrow(() -> paymentService.updateStatusPaymentDetails(data));
	}

	@Test
	public void updateStatusPaymentDetailsWithThrowError() throws Exception {
		PaymentDetails data = new PaymentDetails();
		data.setId(112);
		data.setStatus(0);
		assertThrows(InvalidRequestParameterException.class, () -> paymentService.updateStatusPaymentDetails(data));
	}

	@Test
	public void findByTransactionNo() throws Exception {
		GsonService gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "findByTransactionNo");
		PaymentDetails data = (paymentService.findByTransactionNo(Optional.of("12345")));
		String result = objectMapper.writeValueAsString(data);
		assertEquals(expect, result);
	}

}
