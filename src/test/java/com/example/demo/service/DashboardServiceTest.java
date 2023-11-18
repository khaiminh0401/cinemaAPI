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
        String result = objectMapper.writeValueAsString(dashboardService.findTotalPriceTicketPerMonthOfYear(2023,"Bình Tân"));
        assertEquals(expect, result);
    }

    @Test
    public void testStatisticsTicketPriceByMovie() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "statisticsTicketPriceByMovie");
        String result = objectMapper.writeValueAsString(dashboardService.statisticsTicketPriceByMovie("Hưng Thịnh"));
        assertEquals(expect,result);
    }
    
	@Test
	public void testStatisticsTicketPriceByMovieIsNotFound() throws InvalidRequestParameterException {
		assertThrows(InvalidRequestParameterException.class, () -> dashboardService.statisticsTicketPriceByMovie("Hưng Thịnh 2"));
	}

    
    @Test
    public void testStatisticsTicketPriceByMovieDetail() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "statisticsTicketPriceByMovie2");
        String result = objectMapper.writeValueAsString(dashboardService.statisticsTicketPriceByMovie2("NGƯỢC DÒNG THỜI GIAN ĐỂ YÊU ANH",2023,"Hưng Thịnh"));
        assertEquals(expect,result);
    }
    
    @Test
    public void testStatisticsTicketPriceByMovieDetailIsNotFound() throws Exception {
		assertThrows(InvalidRequestParameterException.class, () -> dashboardService.statisticsTicketPriceByMovie2("NGƯỢC DÒNG THỜI GIAN ĐỂ YÊU ANH2",2023,"Hưng Thịnh"));
    }
    
    @Test
    public void testFillYear() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "fillYear");
        String result = objectMapper.writeValueAsString(dashboardService.fillYear());
        assertEquals(expect,result);
    }
    
    @Test
    public void testStatisticsTicketPriceByMovieForDay() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "statisticsTicketPriceByMovieForDay");
        String result = objectMapper.writeValueAsString(dashboardService.statisticsTicketPriceByMovieForDay("BỘ TỨ DỊ GIỚI: THẾ GIỚI SONG SONG","2023-11-01","cn2"));
        assertEquals(expect,result);
    }
    
	@Test
	public void testStatisticsTicketPriceByMovieForDayIsNotFound() throws InvalidRequestParameterException {
		assertThrows(InvalidRequestParameterException.class, () -> dashboardService.statisticsTicketPriceByMovieForDay("BỘ TỨ DỊ GIỚI: THẾ GIỚI SONG SONG2","2023-11-01","cn2"));
	}
    
    @Test
    public void testStatisticsTicketPriceByMovieFromDate() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "statisticsTicketPriceByMovieFromDate");
        String result = objectMapper.writeValueAsString(dashboardService.statisticsTicketPriceByMovieFromDate("","2023-01-01","2023-02-01","cn2"));
        assertEquals(expect,result);
    }
    
	@Test
	public void testStatisticsTicketPriceByMovieFromDateIsNotFound() throws InvalidRequestParameterException {
		assertThrows(InvalidRequestParameterException.class, () -> dashboardService.statisticsTicketPriceByMovieFromDate("","2023-01-01","2023-02-01","cn3"));
	}
    
    @Test
    public void testMovieOfBranch() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "movieOfBranch");
        String result = objectMapper.writeValueAsString(dashboardService.MovieOfBranch("cn2"));
        assertEquals(expect,result);
    }
    
	@Test
	public void testMovieOfBranchIsNotFound() throws InvalidRequestParameterException {
		assertThrows(InvalidRequestParameterException.class, () -> dashboardService.MovieOfBranch("cn6"));
	}
    
    @Test
    public void testStatisticsTotalShowtimeOfYear() throws Exception {
        String expect = gsonService.getValueExpect(this.getClass().toString(), "statisticsTotalShowtimeOfYear");
        String result = objectMapper.writeValueAsString(dashboardService.statisticsTotalShowtimeOfYear(2023,"cn2"));
        assertEquals(expect,result);
    }

}
