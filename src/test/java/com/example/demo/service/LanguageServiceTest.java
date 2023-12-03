package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.MovieTestApplication;
import com.example.demo.config.GsonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
public class LanguageServiceTest {

	@Autowired
	private GsonService gsonService;

	@Autowired
	private LanguageService languageService;

    @Test
    public void testFindAll() throws JsonProcessingException {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "findAll");
        String actual = gsonService.exportAndGetActual(this.getClass().toString(), "findAll",languageService.findAll());
        assertEquals(expect, actual);
    }
    
}

