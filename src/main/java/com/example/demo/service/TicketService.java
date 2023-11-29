package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.admin.controller.enums.RequestParameterEnum;
import com.example.demo.admin.controller.enums.RequestStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TicketDao;
import com.example.demo.dto.TicketDto;
import com.example.demo.entity.Ticket;
import com.example.demo.exception.InvalidRequestParameterException;

@Service
public class TicketService {
	@Autowired
	TicketDao ticketDao;

	public List<Ticket> findAll() {
		return ticketDao.findAll();
	}

	public Ticket findById(Optional<Integer> id) throws InvalidRequestParameterException {
		id.orElseThrow(() ->
				new InvalidRequestParameterException("Ticket id", RequestParameterEnum.NOTHING));
		
		Ticket ticket = ticketDao.findById(id.get());
		if (ticket == null)
			throw new InvalidRequestParameterException("Ticket", RequestParameterEnum.NOT_FOUND);

		return ticket;
	}

	public List<TicketDto> findByBillId(Optional<Integer> billId) throws InvalidRequestParameterException {
		if (billId.isEmpty())
			throw new InvalidRequestParameterException("Ticket", RequestParameterEnum.NOTHING);
		
		List<TicketDto> tickets = ticketDao.findByBillId(billId.get());
		if (tickets.isEmpty())
			throw new InvalidRequestParameterException("Tickets", RequestParameterEnum.NOT_FOUND);

		return tickets;
	}

	public String insert(Optional<Ticket> ticket) throws InvalidRequestParameterException {
		if (ticket.isEmpty()) throw new InvalidRequestParameterException("Ticket", RequestParameterEnum.NOTHING);

		ticketDao.insert(ticket.get());
		return RequestStatusEnum.SUCCESS.getResponse();
	}
}
