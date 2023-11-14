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
import com.example.demo.exception.InvalidRequestParameterException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void testMethodGetBillHistorySuccess() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String expect = gsonService.getValueExpect(this.getClass().toString(), "testMethodGetBillHistorySuccess");
        String actual = mapper.writeValueAsString(billService.getBillHistory(Optional.of(12)));
        assertEquals(expect, actual);
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

    @Test
    void testMethodFindByMovieSuccess() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String expect = gsonService.getValueExpect(this.getClass().toString(), "testMethodFindByMovieSuccess");
        String actual = mapper.writeValueAsString(billService.findByMovie("MP01"));
        assertEquals(expect, actual);
    }

    @Test
    public void testMethodFindByIdWithoutId() {
        assertThrows(InvalidRequestParameterException.class, () -> {
            billService.findById(Optional.empty());
        });
    }

    @Test
    public void testMethodFindByIdSuccess() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String expect = gsonService.getValueExpect(this.getClass().toString(), "testMethodFindByIdSuccess");
        String actual = mapper.writeValueAsString(billService.findById(Optional.of(21)));
        assertEquals(expect, actual);
    }

}
