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
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
public class DashboardServiceTest {

	@Autowired
	private GsonService gsonService;

	@Autowired
	private DashboardService dashboardService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testFindTotalPriceTicketPerMonthOfYear() throws Exception {

		String expect = gsonService.getValueExpect(this.getClass().toString(), "findTotalPriceTicketPerMonthOfYear");
		String actual = gsonService.exportAndGetActual(this.getClass().toString(), "findTotalPriceTicketPerMonthOfYear",
				dashboardService.findTotalPriceTicketPerMonthOfYear(2023, "cn2"));
		assertEquals(expect, actual);

	}

	@Test
	public void testStatisticsTicketPriceByMovie() throws Exception {
		String expect = gsonService.getValueExpect(this.getClass().toString(), "statisticsTicketPriceByMovie");
		String actual = gsonService.exportAndGetActual(this.getClass().toString(), "statisticsTicketPriceByMovie",
				dashboardService.statisticsTicketPriceByMovie("cn2"));
		assertEquals(expect, actual);
	}

	@Test
	public void testStatisticsTicketPriceByMovieIsNotFound() throws InvalidRequestParameterException {
		assertThrows(InvalidRequestParameterException.class, () -> dashboardService.statisticsTicketPriceByMovie("Hưng Thịnh 2"));
	}

    
    @Test
    public void testStatisticsTicketPriceByMovieDetail() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "statisticsTicketPriceByMovie2");
        String actual = gsonService.exportAndGetActual(this.getClass().toString(), "statisticsTicketPriceByMovie2",dashboardService.statisticsTicketPriceByMovie2("MP07",2023,"cn2"));
        assertEquals(expect,actual);
    }
    
    @Test
    public void testStatisticsTicketPriceByMovieDetailIsNotFound() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> dashboardService.statisticsTicketPriceByMovie2("NGƯỢC DÒNG THỜI GIAN ĐỂ YÊU ANH2",2023,"Hưng Thịnh"));
    }
    
    @Test
    public void testFillYear() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "fillYear");
        String actual = gsonService.exportAndGetActual(this.getClass().toString(), "fillYear",dashboardService.fillYear());
        assertEquals(expect,actual);
    }
    
    @Test
    public void testStatisticsTicketPriceByMovieForDay() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "statisticsTicketPriceByMovieForDay");
        String actual = gsonService.exportAndGetActual(this.getClass().toString(), "statisticsTicketPriceByMovieForDay",dashboardService.statisticsTicketPriceByMovieForDay("","2023-11-01","cn2"));
        assertEquals(expect,actual);
    }
    
	@Test
	public void testStatisticsTicketPriceByMovieForDayIsNotFound() throws InvalidRequestParameterException {
		assertThrows(InvalidRequestParameterException.class, () -> dashboardService.statisticsTicketPriceByMovieForDay("BỘ TỨ DỊ GIỚI: THẾ GIỚI SONG SONG2","2023-11-01","cn2"));
	}
    
    @Test
    public void testStatisticsTicketPriceByMovieFromDate() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "statisticsTicketPriceByMovieFromDate");
        String actual = gsonService.exportAndGetActual(this.getClass().toString(), "statisticsTicketPriceByMovieFromDate",dashboardService.statisticsTicketPriceByMovieFromDate("","2023-11-01","2023-11-02","cn2"));
        assertEquals(expect,actual);
    }
    
	@Test
	public void testStatisticsTicketPriceByMovieFromDateIsNotFound() throws InvalidRequestParameterException {
		assertThrows(InvalidRequestParameterException.class, () -> dashboardService.statisticsTicketPriceByMovieFromDate("","2023-01-01","2023-02-01","cn3"));
	}
    
    @Test
    public void testMovieOfBranch() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "movieOfBranch");
        String actual = gsonService.exportAndGetActual(this.getClass().toString(), "movieOfBranch",dashboardService.MovieOfBranch("cn1"));
        System.out.println(actual);
        assertEquals(expect,actual);
    }
    
	@Test
	public void testMovieOfBranchIsNotFound() throws InvalidRequestParameterException {
		assertThrows(InvalidRequestParameterException.class, () -> dashboardService.MovieOfBranch("cn6"));
	}
    
    @Test
    public void testStatisticsTotalShowtimeOfYear() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "statisticsTotalShowtimeOfYear");
        String actual = gsonService.exportAndGetActual(this.getClass().toString(), "statisticsTotalShowtimeOfYear",dashboardService.statisticsTotalShowtimeOfYear(2023,"cn2"));
        assertEquals(expect,actual);
    }

}
