package com.example.demo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.dao.MovieDao;
import com.example.demo.entity.Movie;
import com.example.demo.service.MovieService;


@RunWith(MockitoJUnitRunner.class)
public class TestFindID_movie {
	
	@Mock
	private MovieDao movieDao;
	
	@InjectMocks
	private MovieService moviesv;
	
	@Test
	public void testFindById() {
		// giải lập
		Movie exMovie = new Movie("MP01","QUÁI VẬT SÔNG MEKONG","Vithaya Pansringarm, Sushar Manaying, Teerapat Satjakul",2022,"MP01.png","Lee Thongkham","Thái Lan","105 phút","Một con quái vật bí ẩn bỗng xuất hiện từ sông Mekong và tấn công vùng Bueng Kan, nó hủy diệt mọi thứ và khiến người dân mất kết nối hoàn toàn với thế giới bên ngoài. Sự kiện gây chấn động toàn Thái Lan này đã khiến các cơ quan chức năng cùng những nhà khoa học vô tình đến Bueng Kan đã tiến hành nghiên cứu phải huy động tất cả các lực lượng để bắt con quái vật điên rồ này trước khi quá muộn","https://www.youtube.com/watch?v=orcxSddIA4s");
		when(movieDao.findById("MP01")).thenReturn(exMovie);
	    // gọi phương thức cần kiểm tra
		Movie actualMovie = moviesv.findById("MP01");
		assertEquals(exMovie, actualMovie);
		verify(movieDao).findById("MP01");
	}
	
	
}
