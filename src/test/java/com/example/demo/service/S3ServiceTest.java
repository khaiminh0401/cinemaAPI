package com.example.demo.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.demo.MovieTestApplication;
import com.example.demo.config.GsonService;
import com.example.demo.util.FileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

@SpringBootTest(classes = MovieTestApplication.class)
@AutoConfigureMockMvc
public class S3ServiceTest {
	@Autowired
	private S3Service s3Service;

	@Autowired
	private AmazonS3 amazonS3;

	@Autowired
	private GsonService gsonService;

	final String BUCKET_NAME = "zuhot-cinema-images";

	@Test
	public void saveFile() throws AmazonServiceException, IOException {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\movie\\MP51.jpg");
		MockMultipartFile multipartFile = new MockMultipartFile("image", "MP51.jpg", "image/jpg", fis);
		String folder = "poster-movie/";
		String extension = FileUtils.getExtension(multipartFile.getOriginalFilename());
		String fileName = "MP51";
		String key = folder + fileName + "." + extension;
		InputStream inputStream = multipartFile.getInputStream();
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType("image/" + extension);
		// Lưu movie poster tới S3 bucket
		assertDoesNotThrow(() -> s3Service.saveFile(BUCKET_NAME, key, inputStream, objectMetadata));
	}

	@Test
	public void deleteFile() throws AmazonServiceException {
		assertDoesNotThrow(() -> s3Service.deleteFile(BUCKET_NAME, "poster-movie/MP51.jpg"));
	}

	@Test
	public void saveFileWithBucketIsWrong() throws AmazonServiceException, IOException {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\movie\\MP51.jpg");
		MockMultipartFile multipartFile = new MockMultipartFile("image", "MP51.jpg", "image/jpg", fis);
		String folder = "poster-movie/";
		String extension = FileUtils.getExtension(multipartFile.getOriginalFilename());
		String fileName = "MP51";
		String key = folder + fileName + "." + extension;
		InputStream inputStream = multipartFile.getInputStream();
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType("image/" + extension);
		
		// Lưu movie poster tới S3 bucket
		assertThrows(AmazonServiceException.class,
				() -> s3Service.saveFile("", key, inputStream, objectMetadata));
	}
	
	@Test
	public void deleteFileWithBucketIsWrong() throws AmazonServiceException {
		assertThrows(AmazonServiceException.class,() -> s3Service.deleteFile("", "poster-movie/MP51.jpg"));
	}
}
