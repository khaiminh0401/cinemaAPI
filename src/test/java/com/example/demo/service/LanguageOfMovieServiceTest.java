package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.MovieTestApplication;
import com.example.demo.config.GsonService;
import com.example.demo.exception.InvalidRequestParameterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
public class LanguageOfMovieServiceTest {

	@Autowired
	private GsonService gsonService;

	@Autowired
	private LanguageOfMovieService languageOfMovieService;

	@Autowired
	private ObjectMapper objectMapper;

    @Test
    public void testFindAll() throws JsonProcessingException {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "findAll");
        String result = objectMapper.writeValueAsString(languageOfMovieService.findAll());
        assertEquals(expect, result);
    }
    
    @Test
    public void testFindByMovieId() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "findByMovieId");
        String result = objectMapper.writeValueAsString(languageOfMovieService.findByMovieId("MP01"));
        assertEquals(expect, result);
    }
    

	@Test
	public void testFindByMovieIdIsNotFound() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> languageOfMovieService.findByMovieId("MP00"));
	}

	@Test
	public void testFindByMovieIdIsNull() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> languageOfMovieService.findByMovieId(null));
	}

}
