package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.MovieTestApplication;
import com.example.demo.config.GsonService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
public class BranchServiceTest {
    @Autowired
    private GsonService gsonService;

    @Autowired
    private BranchService branchService;

    @Test
    public void testMethodFindAll() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String expect = gsonService.getValueExpect(this.getClass().toString(), "testMethodFindAll");
        String actual = mapper.writeValueAsString(branchService.findAll());
        assertEquals(expect, actual);
    }
}
