package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.enums.RequestStatusEnum;
import com.example.demo.config.SecurityConfig;
import com.example.demo.dao.StaffDao;
import com.example.demo.entity.Staff;
import com.example.demo.exception.InvalidRequestParameterException;

@Service
public class StaffService {
	@Autowired
	private StaffDao staffDao;
	@Autowired
	private SecurityConfig securityConfig;

	public RequestStatusEnum insert(Staff staff) throws InvalidRequestParameterException{
		staff.setPassword(securityConfig.passwordEncoder().encode(staff.getPassword()));
		return (staffDao.insert(staff)== 1 ? RequestStatusEnum.SUCCESS : RequestStatusEnum.FAILURE);
	}
}
