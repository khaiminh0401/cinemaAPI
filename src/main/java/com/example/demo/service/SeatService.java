package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.admin.controller.enums.RequestParameterEnum;
import com.example.demo.dao.SeatDao;
import com.example.demo.dto.SeatDto;
import com.example.demo.exception.InvalidRequestParameterException;
import com.example.demo.model.SeatModel;

@Service
public class SeatService {
    @Autowired
    private SeatDao seatDao;

    /**
     * Get seat by roomid
     * 
     * @param id
     * @return SeatHasCheckTicket
     * @throws InvalidRequestParameterException 
     */
    public List<SeatDto> findByRoomId(Optional<String> id) throws InvalidRequestParameterException {
    	id.orElseThrow(() -> new InvalidRequestParameterException("Seat id", RequestParameterEnum.NOTHING));
    	List<SeatDto> seat = seatDao.findByRoomId(id.get());
    	if (seat.isEmpty()) throw new InvalidRequestParameterException("Seat id", RequestParameterEnum.NOT_FOUND);
    	
        return seat;
    }

    public SeatDto getTotal(Optional<Integer> showtimeid, Optional<String> name) throws InvalidRequestParameterException{
    	showtimeid.orElseThrow(() -> new InvalidRequestParameterException("Seat showtimeid", RequestParameterEnum.NOTHING));
    	name.orElseThrow(() -> new InvalidRequestParameterException("Seat name", RequestParameterEnum.NOTHING));
    	
    	SeatDto seat = seatDao.getPriceSeatByShowTimeAndSeatId(showtimeid.get(), name.get());
    	if (seat == null) throw new InvalidRequestParameterException("Seat id", RequestParameterEnum.NOT_FOUND);
    	
        return seat;
    }

    /**
     * Get seat orderd or not
     * 
     * @param id
     * @return SeatModel
     * @throws InvalidRequestParameterException 
     */
    public List<?> getSeatHasCheckTicket(Optional<Integer> id) throws InvalidRequestParameterException {
    	id.orElseThrow(() -> new InvalidRequestParameterException("Seat id", RequestParameterEnum.NOTHING));
    	List<?> seat = seatDao.getSeatHasCheckTicket(id.get()).stream().map(s->new SeatModel(s)).toList();
    	if (seat.isEmpty()) throw new InvalidRequestParameterException("Seat id", RequestParameterEnum.NOT_FOUND);
    	
        return seat;
    }
}
