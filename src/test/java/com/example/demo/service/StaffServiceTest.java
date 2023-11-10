package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.MovieApplication;
import com.example.demo.admin.controller.enums.RequestParameterEnum;
import com.example.demo.config.GsonService;
import com.example.demo.entity.Staff;
import com.example.demo.exception.InvalidRequestParameterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = MovieApplication.class)
@AutoConfigureMockMvc
public class StaffServiceTest {
	@Autowired
    private StaffService staffService;

	@Autowired
	private GsonService gsonService;

	@Autowired
	private ObjectMapper objectMapper;
	
	private Staff staff;
    
    @Test
	public void testInsert() throws Exception {
    	staff = new Staff("NDT1", "branch1", "John Doe", true, "password123",
                new Date(0), "123456789", "john.doe@example.com", true, 1);
		assertDoesNotThrow(() -> staffService.insert(staff));
	}
    
    @Test
	public void testInsertWithThowError() throws Exception {
    	staff = new Staff("NDT1", "branch1", "John Doe", true, "password123",
                new Date(0), "123456789", "john.doe@example.com", true, 1);
		assertThrows(InvalidRequestParameterException.class, () -> staffService.insert(staff));
	}
}
