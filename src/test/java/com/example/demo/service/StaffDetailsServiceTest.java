package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.MovieApplication;
import com.example.demo.admin.controller.enums.RequestParameterEnum;
import com.example.demo.config.GsonService;
import com.example.demo.exception.InvalidRequestParameterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = MovieApplication.class)
@AutoConfigureMockMvc
public class StaffDetailsServiceTest {
	@Autowired
	private StaffDetailsService staffDetailsService;

	@Autowired
	private GsonService gsonService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testLoadUserByUsername() throws JsonProcessingException {
		gsonService = new GsonService();
		String expect = gsonService.getValueExpect(this.getClass().toString(), "loadUserByUsername");
		String result = objectMapper.writeValueAsString(staffDetailsService.loadUserByUsername("duc@gmail.com"));
		assertEquals(expect, result);
	}

	@Test
	public void testLoadUserByUsernameIsNull() throws Exception {
		InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> staffDetailsService.loadUserByUsername(null));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("Staff Details", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOTHING.getName(), exception.getResponse().getParam());
	}

	@Test
    public void testLoadUserByUsernameIsNotPresent() throws Exception {
    	String email = "duck#@gmail.com";
    	
    	UsernameNotFoundException exception = assertThrows(
         		UsernameNotFoundException.class,
                 () -> staffDetailsService.loadUserByUsername(email));
    	UsernameNotFoundException result = new UsernameNotFoundException("Không tồn tại người dùng " + email);
        assertEquals(result.getMessage(), exception.getMessage());
    }

	@Test
	public void testLoadUserByUsernameIsEmpty() throws Exception {
		InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> staffDetailsService.loadUserByUsername(null));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("Staff Details", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOTHING.getName(), exception.getResponse().getParam());
	}
}
