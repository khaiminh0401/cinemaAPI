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

import com.example.demo.MovieTestApplication;
import com.example.demo.config.GsonService;
import com.example.demo.entity.TokenVnpay;
import com.example.demo.exception.InvalidRequestParameterException;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest(classes = MovieTestApplication.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@AutoConfigureMockMvc
public class TokenVnpayServiceTest {
    @Autowired
    private GsonService gsonService;

    @Autowired
    TokenVnpayService tokenVnpayService;

    @Test
    @DatabaseSetup(value = "/db/tokenvnpay.xml")
    @ExpectedDatabase(value = "/expecteddb/tokenvnpay.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void insert() throws Exception {
        TokenVnpay tokenVnpay = GsonService.toObject(
                gsonService.getValueInput(this.getClass().toString(),
                        Thread.currentThread().getStackTrace()[1].getMethodName()),
                TokenVnpay.class);
        String expect = gsonService.getValueExpect(this.getClass().toString(),
                Thread.currentThread().getStackTrace()[1].getMethodName());
        String actual = gsonService.exportAndGetActual(this.getClass().toString(), "insert",
                tokenVnpayService.insert(Optional.of(tokenVnpay)));
        assertEquals(expect, actual);
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
    @DatabaseSetup(value = "/db/tokenvnpay.xml")
    public void findByCustomerId() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(),
                Thread.currentThread().getStackTrace()[1].getMethodName());
        String actual = gsonService.exportAndGetActual(this.getClass().toString(), "findByCustomerId",
                tokenVnpayService.findByCustomerId(Optional.of(16)));
        assertEquals(expect, actual);
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
