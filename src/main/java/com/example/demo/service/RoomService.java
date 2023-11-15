package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.controller.enums.RequestParameterEnum;
import com.example.demo.dao.RoomDao;
import com.example.demo.dto.RoomDto;
import com.example.demo.exception.InvalidRequestParameterException;

@Service
public class RoomService {
	@Autowired
	RoomDao roomDao;

	public List<RoomDto> findAll() {
		return roomDao.findAll();
	}

	public Optional<RoomDto> findById(String id) throws InvalidRequestParameterException {
		return Optional.of(roomDao.findById(id))
				.orElseThrow(() -> new InvalidRequestParameterException("Room", RequestParameterEnum.NOT_FOUND));
	}

	public List<RoomDto> getByBranch(String id, String showdate) throws InvalidRequestParameterException {
		List<RoomDto> list = roomDao.getByBranch(id, showdate);
		if (list.size() <= 0) {
			throw new InvalidRequestParameterException("Room", RequestParameterEnum.NOT_FOUND);
		}
		return list;
	}
}
