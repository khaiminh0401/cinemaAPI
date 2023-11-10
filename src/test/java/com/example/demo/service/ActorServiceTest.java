package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.example.demo.MovieTestApplication;
import com.example.demo.admin.controller.enums.RequestStatusEnum;
import com.example.demo.config.GsonService;
import com.example.demo.entity.Actor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest(classes = MovieTestApplication.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@AutoConfigureMockMvc
public class ActorServiceTest {
    @Autowired
    private GsonService gsonService;

    @Autowired
    private ActorService actorService;

    @Test
    public void testMethodFindAll() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String expect = gsonService.getValueExpect(this.getClass().toString(), "testMethodFindAll");
        String actual = mapper.writeValueAsString(actorService.findAll());
        assertEquals(expect, actual);
    }

    @DatabaseSetup(value = "/db/ActorServiceTest_testInsertActorSuccess_db.xml")
	@ExpectedDatabase(value = "/expecteddb/ActorServiceTest_testInsertActorSuccess_db_expect.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    @Test
    public void testInsertActorSuccess() throws Exception{
        Actor actor = new Actor();
        actor.setName("Trường Giang");
        this.actorService.insert(actor);
    }
}
