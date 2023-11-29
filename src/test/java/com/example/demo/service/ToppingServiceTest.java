package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.example.demo.MovieTestApplication;
import com.example.demo.config.GsonService;
import com.example.demo.entity.ToppingDetails;
import com.example.demo.exception.InvalidRequestParameterException;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest(classes = MovieTestApplication.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@AutoConfigureMockMvc
public class ToppingServiceTest {
    @Autowired
    private GsonService gsonService;

    @Autowired
    ToppingService toppingService;

    @Test
    @DatabaseSetup(value = "/db/ToppingOfBranch.xml")
    public void findToppingOfBranchById() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(),
                Thread.currentThread().getStackTrace()[1].getMethodName());
        String actual = gsonService.exportAndGetActual(this.getClass().toString(), "findToppingOfBranchById",
                toppingService.findToppingOfBranchById(1));
        assertEquals(expect, actual);
    }

    @Test
    public void findToppingOfBranchByIdInvalid() throws Exception {
        assertThrows(InvalidRequestParameterException.class, () -> toppingService.findToppingOfBranchById(0));
    }

    @Test
    public void findToppingOfBranchByIdIsNull() throws Exception {
        assertThrows(InvalidRequestParameterException.class, () -> toppingService.findToppingOfBranchById(null));
    }

    @Test
    @DatabaseSetup(value = "/db/ToppingDto.xml")
    public void findByBranchid() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(),
                Thread.currentThread().getStackTrace()[1].getMethodName());
        String actual = gsonService.exportAndGetActual(this.getClass().toString(), "findToppingOfBranchById",
                toppingService.findByBranchid(Optional.of("cn1")));
        assertEquals(expect, actual);
    }

    @Test
    public void findByBranchidInvalid() throws Exception {
        assertThrows(InvalidRequestParameterException.class, () -> toppingService.findByBranchid(Optional.of("cn0")));
    }

    @Test
    public void findByBranchidIsNull() throws Exception {
        assertThrows(InvalidRequestParameterException.class, () -> toppingService.findByBranchid(Optional.of(null)));
    }

    @Test
    public void findByBranchidIsEmpty() throws Exception {
        assertThrows(InvalidRequestParameterException.class, () -> toppingService.findByBranchid(Optional.of("")));
    }

    @Test
    public void orderTopping() throws Exception {
        ToppingDetails toppingDetails = GsonService.toObject(gsonService.getValueInput(this.getClass().toString(),
                Thread.currentThread().getStackTrace()[1].getMethodName()), ToppingDetails.class);
        String expect = gsonService.getValueExpect(this.getClass().toString(),
                Thread.currentThread().getStackTrace()[1].getMethodName());
        String actual = gsonService.exportAndGetActual(this.getClass().toString(), "findToppingOfBranchById",
                toppingService.orderTopping(Optional.of(toppingDetails)));
        assertEquals(expect, actual);
    }

    @Test
    public void orderToppingInvalid() throws Exception {
        ToppingDetails toppingDetails = GsonService.toObject(gsonService.getValueInput(this.getClass().toString(),
                Thread.currentThread().getStackTrace()[1].getMethodName()), ToppingDetails.class);
        assertThrows(InvalidRequestParameterException.class,
                () -> toppingService.orderTopping(Optional.of(toppingDetails)));
    }

    @Test
    public void orderToppingIsNull() throws Exception {
        assertThrows(InvalidRequestParameterException.class, () -> toppingService.orderTopping(Optional.of(null)));
    }

    @Test
    @DatabaseSetup(value = "/db/ToppingOfBranch.xml")
    @ExpectedDatabase(value = "/expecteddb/ToppingOfBranch.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    public void updateToppingOfBranchAfterOrdered() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(),
                Thread.currentThread().getStackTrace()[1].getMethodName());
        String actual = gsonService.exportAndGetActual(this.getClass().toString(), "findToppingOfBranchById",
                toppingService.updateToppingOfBranchAfterOrdered(1, 1));
        assertEquals(expect, actual);
    }

    @Test
    public void updateToppingOfBranchAfterOrderedWithIdAndQuantityZero() throws Exception {
        assertThrows(InvalidRequestParameterException.class,
                () -> toppingService.updateToppingOfBranchAfterOrdered(0, 0));
    }
}
