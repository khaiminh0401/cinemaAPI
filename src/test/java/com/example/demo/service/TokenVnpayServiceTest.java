package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.MovieTestApplication;
import com.example.demo.config.GsonService;
import com.example.demo.entity.TokenVnpay;

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
public class TokenVnpayServiceTest {
    @Autowired
    private GsonService gsonService;

    @Autowired
    TokenVnpayService tokenVnpayService;

    @Test
    public void insert() throws Exception {
        TokenVnpay tokenVnpay = GsonService.toObject(gsonService.getValueInput(this.getClass().toString(), "insert"),
                TokenVnpay.class);
        Integer result = tokenVnpayService.insert(tokenVnpay);
        Integer expect = 1;
        assertEquals(result, expect);
    }
}
