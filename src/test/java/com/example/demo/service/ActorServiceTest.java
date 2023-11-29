package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.example.demo.MovieTestApplication;
import com.example.demo.config.GsonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@SpringBootTest(classes = MovieTestApplication.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@AutoConfigureMockMvc
@DatabaseSetup(value = "/db/actor.xml")
public class ActorServiceTest {
    @Autowired
    private GsonService gsonService;

    @Autowired
    private ActorService actorService;

    @Test
    public void testMethodFindAll() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "testMethodFindAll");
        String actual = gsonService.exportAndGetActual(this.getClass().toString(), "testMethodFindAll",actorService.findAll());
        assertEquals(expect, actual);
    }
}
