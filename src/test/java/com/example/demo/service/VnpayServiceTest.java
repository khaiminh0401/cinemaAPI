package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.InvocationTargetException;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.example.demo.MovieTestApplication;
import com.example.demo.config.GsonService;
import com.example.demo.dto.VnpayPaymentDto;
import com.example.demo.dto.VnpayToken;
import com.example.demo.exception.InvalidRequestParameterException;
import com.github.springtestdbunit.DbUnitTestExecutionListener;

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class VnpayServiceTest {
    @Autowired
    private GsonService gsonService;

    @Autowired
    VnpayService vnpayService;

    private final TestRestTemplate restTemplate = new TestRestTemplate();
    
    @Test
    public void createPayment() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("vnp_Amount", "1000");
        request.addHeader("vnp_OrderInfo", "Pay");
        VnpayPaymentDto vnpayPaymentDto = new VnpayPaymentDto();
        vnpayPaymentDto.setVnp_OrderInfo("Zuhot Ticket");
        vnpayPaymentDto.setVnp_Amount("250000");
        vnpayPaymentDto.setVnp_BankCode("NCB");

        String result = vnpayService.createPayment(request, vnpayPaymentDto);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(result, String.class);
        assertEquals(HttpStatus.SC_OK + " OK", responseEntity.getStatusCode().toString());
    }

    @Test
    public void createToken() throws InvalidRequestParameterException {
        VnpayToken vnpayToken = new VnpayToken();
        vnpayToken.setVnp_txn_desc("create token");
        vnpayToken.setVnp_app_user_id("4");
        vnpayToken.setVnp_bank_code("9704198526191432198");
        vnpayToken.setVnp_card_type("01");
        String result = vnpayService.createToken(null, vnpayToken);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(result, String.class);
        assertEquals(HttpStatus.SC_OK + " OK", responseEntity.getStatusCode().toString());
    }

    @Test
    public void paymentAndCreateToken() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException,
            InvalidRequestParameterException {
        VnpayToken vnpayToken = new VnpayToken();
        vnpayToken.setVnp_txn_desc("payment and create token");
        vnpayToken.setVnp_app_user_id("4");
        vnpayToken.setVnp_bank_code("9704198526191432198");
        vnpayToken.setVnp_card_type("01");
        vnpayToken.setVnp_amount("100000");
        String result = vnpayService.paymentAndCreateToken(null, vnpayToken);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(result, String.class);
        assertEquals(HttpStatus.SC_OK + " OK", responseEntity.getStatusCode().toString());
    }

    @Test
    public void paymentByToken() throws InvalidRequestParameterException {
        VnpayToken vnpayToken = new VnpayToken();
        vnpayToken.setVnp_txn_desc("payment by token");
        vnpayToken.setVnp_app_user_id("4");
        vnpayToken.setVnp_token("thistoken");
        vnpayToken.setVnp_amount("100000");
        String result = vnpayService.paymentByToken(null, vnpayToken);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(result, String.class);
        assertEquals(HttpStatus.SC_OK + " OK", responseEntity.getStatusCode().toString());
    }

    @Test
    public void removeToken() throws InvalidRequestParameterException {
        VnpayToken vnpayToken = new VnpayToken();
        vnpayToken.setVnp_txn_desc("remove token");
        vnpayToken.setVnp_app_user_id("4");
        vnpayToken.setVnp_token("thistoken");
        String result = vnpayService.removeToken(null, vnpayToken);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(result, String.class);
        assertEquals(HttpStatus.SC_OK + " OK", responseEntity.getStatusCode().toString());
    }
}
