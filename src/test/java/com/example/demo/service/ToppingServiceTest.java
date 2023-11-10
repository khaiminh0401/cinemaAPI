package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.MovieTestApplication;
import com.example.demo.admin.controller.enums.RequestParameterEnum;
import com.example.demo.config.GsonService;
import com.example.demo.entity.ToppingDetails;
import com.example.demo.exception.InvalidRequestParameterException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
public class ToppingServiceTest {
    @Autowired
    private GsonService gsonService;

    @Autowired
    ToppingService toppingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void findToppingOfBranchById() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(),
                Thread.currentThread().getStackTrace()[1].getMethodName());
        String result = objectMapper.writeValueAsString(toppingService.findToppingOfBranchById(1));
        assertEquals(expect, result);
    }

    @Test
    public void findToppingOfBranchByIdIsEmpty() throws Exception {
        InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> toppingService.findToppingOfBranchById(1));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("Topping", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOTHING.getName(), exception.getResponse().getParam());
    }

    @Test
    public void findByBranchid() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(),
                Thread.currentThread().getStackTrace()[1].getMethodName());
        String result = objectMapper.writeValueAsString(toppingService.findByBranchid(Optional.of("cn1")));
        assertEquals(expect, result);
    }

    @Test
    public void findByBranchidIsEmpty() throws Exception {
        InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> toppingService.findByBranchid(Optional.empty()));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("Topping", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOTHING.getName(), exception.getResponse().getParam());
    }

    @Test
    public void orderTopping() throws Exception {
        ToppingDetails toppingDetails = GsonService.toObject(gsonService.getValueInput(this.getClass().toString(),
                Thread.currentThread().getStackTrace()[1].getMethodName()), ToppingDetails.class);
        String expect = "RS_02";
        String result = toppingService.orderTopping(Optional.of(toppingDetails));
        assertEquals(expect, result);
    }

    @Test
    public void orderToppingIsEmpty() throws Exception {
        InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> toppingService.orderTopping(Optional.empty()));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("Topping Details", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOTHING.getName(), exception.getResponse().getParam());
    }

    @Test
    public void updateToppingOfBranchAfterOrderedWithIdAndQuantityZero() throws Exception {
        InvalidRequestParameterException exception = assertThrows(
                InvalidRequestParameterException.class,
                () -> toppingService.updateToppingOfBranchAfterOrdered(0, 0));
        assertEquals(400, exception.getResponse().getStatusCode());
        assertEquals("ToppingId", exception.getResponse().getMessage());
        assertEquals(RequestParameterEnum.NOT_FOUND.getName(), exception.getResponse().getParam());
    }

    @Test
    public void updateToppingOfBranchAfterOrdered() throws Exception {
        String expect = "RS_02";
        String result = toppingService.updateToppingOfBranchAfterOrdered(1, 1);
        assertEquals(expect, result);
    }
}
