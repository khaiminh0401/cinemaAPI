package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
public class TypeOfMovieServiceTest {
    @Autowired
    private GsonService gsonService;

    @Autowired
    TypeOfMovieService typeOfMovieService;

    @Test
    public void findAll() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "findAll");
        String actual = gsonService.exportAndGetActual(this.getClass().toString(), "findAll",
                typeOfMovieService.findAll());
        assertEquals(expect, actual);
    }

    @Test
    public void findById() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "findById");
        String actual = gsonService.exportAndGetActual(this.getClass().toString(), "findById",
                typeOfMovieService.findById("LP01"));
        assertEquals(expect, actual);
    }

    @Test
    public void findByIdIsNull() {
        assertThrows(InvalidRequestParameterException.class, () -> typeOfMovieService.findById(null));
    }

    @Test
    public void findByIdInvalid() {
        assertThrows(InvalidRequestParameterException.class, () -> typeOfMovieService.findById("0"));
    }
}
