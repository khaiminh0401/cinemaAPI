package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ToppingDao;

@RestController
@RequestMapping("/topping")
@CrossOrigin("*")
public class ToppingController implements BaseController{
	@Autowired
	private ToppingDao dao;
	
	@Override
	@GetMapping(value= {"","/"})
	public ResponseEntity<?> findAll() {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(dao.findAll());
	}

	@Override
	public ResponseEntity<?> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
