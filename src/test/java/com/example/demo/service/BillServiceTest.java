package com.example.demo.service;

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
import com.example.demo.exception.InvalidRequestParameterException;
import com.github.springtestdbunit.DbUnitTestExecutionListener;

@SpringBootTest(classes = MovieTestApplication.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@AutoConfigureMockMvc
public class BillServiceTest {

	@Autowired
	private GsonService gsonService;

    @Autowired
    private BillService billService;

    @Test
    public void testMethodGetBillHistorySuccess() throws InvalidRequestParameterException{
        billService.getBillHistory(Optional.of(1));
    }


    @Test
    public void testMethodGetBillHistoryWithoutCustomerId() {
        assertThrows(InvalidRequestParameterException.class, () -> {
            billService.getBillHistory(Optional.empty());
        });
    }

    @Test
    void testGetBillDetailsWithMissingParameters() {
        assertThrows(InvalidRequestParameterException.class, () -> {
            billService.getBillDetails(Optional.empty(), Optional.empty());
        });
    }
}
