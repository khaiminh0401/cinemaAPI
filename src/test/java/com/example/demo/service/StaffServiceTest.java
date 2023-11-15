package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.example.demo.MovieApplication;
import com.example.demo.entity.Staff;
import com.example.demo.exception.InvalidRequestParameterException;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@SpringBootTest(classes = MovieApplication.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class })
@AutoConfigureMockMvc
public class StaffServiceTest {
	@Autowired
    private StaffService staffService;
	
	private Staff staff;
    
	@DatabaseSetup(value = "/db/StaffServiceTest_testInsertStaffSuccess_db.xml")
	@ExpectedDatabase(value = "/expecteddb/StaffServiceTest_testInsertStaffSuccess_db_expect.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    @Test
	public void testInsertStaffSuccess() throws InvalidRequestParameterException {
    	staff = new Staff();
    	staff.setId("NDT1");
        staff.setBranchId("cn1");
        staff.setName("John Doe");
        staff.setGender(true);
        staff.setPassword("12345678");
        staff.setPhone("123456789");
        staff.setEmail("john.doe@example.com");
        staff.setStatus(true);
        staff.setRole(1);
        staffService.insert(staff);
	}
}
