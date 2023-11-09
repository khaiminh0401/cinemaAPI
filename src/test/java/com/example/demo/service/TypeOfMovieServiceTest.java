package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.MovieTestApplication;
import com.example.demo.admin.controller.enums.RequestParameterEnum;
import com.example.demo.config.GsonService;
import com.example.demo.exception.InvalidRequestParameterException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
public class TypeOfMovieServiceTest {
    @Autowired
    private GsonService gsonService;

    @Autowired
    TypeOfMovieService typeOfMovieService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void findAll() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "findAll");
        String result = objectMapper.writeValueAsString(typeOfMovieService.findAll());
        assertEquals(expect, result);
    }

    @Test
    public void findById() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "findById");
        String result = objectMapper.writeValueAsString(typeOfMovieService.findById("LP01"));
        assertEquals(expect, result);
    }

    @Test
    public void findByIdIsNull() throws Exception {
        Throwable exception = assertThrows(InvalidRequestParameterException.class, () -> {
            throw new InvalidRequestParameterException("Phim", RequestParameterEnum.NOT_FOUND);
        });
        try {
            typeOfMovieService.findById(null);
        } catch (Exception e) {
            assertEquals(e.getMessage(), exception.getMessage());
        }
    }
}
