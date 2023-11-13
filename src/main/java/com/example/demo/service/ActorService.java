package com.example.demo.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.controller.enums.RequestStatusEnum;
import com.example.demo.dao.ActorDao;
import com.example.demo.entity.Actor;
import com.example.demo.exception.InvalidRequestParameterException;

@Service
public class ActorService {
	@Autowired
	private ActorDao actorDao;

	private static final Logger logger = LoggerFactory.getLogger(ActorService.class);

	public List<Actor> findAll() {
		return actorDao.findAll();
	}

	public RequestStatusEnum insert(Actor actor) throws InvalidRequestParameterException {
		logger.info("RUN -> ActorService");
		logger.info("params: "+actor.toString());
		RequestStatusEnum response = actorDao.insert(actor) == 1 ? RequestStatusEnum.SUCCESS : RequestStatusEnum.FAILURE;
		logger.info("Status:"+response);
		return response;
	}

}
