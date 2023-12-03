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
import com.example.demo.service.DimensionServiceTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
public class DimensionServiceTest {

	@Autowired
	private GsonService gsonService;

	@Autowired
	private DimensionService dimensionService;

	@Autowired
	private ObjectMapper objectMapper;

    @Test
    public void testFindAll() throws JsonProcessingException {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "findAll");
        String actual = gsonService.exportAndGetActual(this.getClass().toString(), "findAll",dimensionService.findAll());
        assertEquals(expect, actual);
    }
    
    @Test
    public void testFindById() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "findById");
        String actual = gsonService.exportAndGetActual(this.getClass().toString(), "findById",dimensionService.findById(1).get());
        System.out.println(actual);
        assertEquals(expect,actual);
    }
    
    @Test
    public void testFindByIdisNull() throws Exception {
    	assertThrows(InvalidRequestParameterException.class, () -> dimensionService.findById(null));
    }
    

    @Test
    public void testFindByIdInvalid() {
        assertThrows(InvalidRequestParameterException.class, () -> dimensionService.findById(0));
    }


}
