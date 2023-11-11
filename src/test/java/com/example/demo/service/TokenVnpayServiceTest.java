package com.example.demo.service;

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
import com.example.demo.entity.TokenVnpay;
import com.example.demo.exception.InvalidRequestParameterException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
public class TokenVnpayServiceTest {
    @Autowired
    private GsonService gsonService;

    @Autowired
    TokenVnpayService tokenVnpayService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void insert() throws Exception {
        TokenVnpay tokenVnpay = GsonService.toObject(gsonService.getValueInput(this.getClass().toString(), "insert"),
                TokenVnpay.class);
        String result = tokenVnpayService.insert(Optional.of(tokenVnpay));
        String expect = "RS_02";
        assertEquals(result, expect);
    }

    @Test
    public void insertInvalid() throws Exception {
        TokenVnpay tokenVnpay = GsonService.toObject(gsonService.getValueInput(this.getClass().toString(),
                Thread.currentThread().getStackTrace()[1].getMethodName()), TokenVnpay.class);
        assertThrows(InvalidRequestParameterException.class, () -> tokenVnpayService.insert(Optional.of(tokenVnpay)));
    }

    @Test
    public void insertIsNull() throws Exception {
        TokenVnpay tokenVnpay = GsonService.toObject(gsonService.getValueInput(this.getClass().toString(),
                Thread.currentThread().getStackTrace()[1].getMethodName()), TokenVnpay.class);
        assertThrows(InvalidRequestParameterException.class, () -> tokenVnpayService.insert(Optional.of(tokenVnpay)));
    }

    @Test
    public void findByCustomerId() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(),
                Thread.currentThread().getStackTrace()[1].getMethodName());
        String result = objectMapper.writeValueAsString(tokenVnpayService.findByCustomerId(Optional.of(1)));
        assertEquals(expect, result);
    }

    @Test
    public void findByCustomerIdInvalid() throws Exception {
        assertThrows(InvalidRequestParameterException.class,
                () -> tokenVnpayService.findByCustomerId(Optional.of(0)));
    }

    @Test
    public void findByCustomerIdIsNull() throws Exception {
        assertThrows(InvalidRequestParameterException.class,
                () -> tokenVnpayService.findByCustomerId(Optional.of(null)));
    }

}
