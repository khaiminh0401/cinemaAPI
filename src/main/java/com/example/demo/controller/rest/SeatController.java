package com.example.demo.controller.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.InvalidRequestParameterException;
import com.example.demo.service.MovieService;
import com.example.demo.service.SeatService;

@RestController
@RequestMapping("/api/seat")
@CrossOrigin("*")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @Autowired
    private MovieService movieService;

    @GetMapping(value ={"/",""})
    public ResponseEntity<?> findByRoomId(@RequestParam("roomid") Optional<String> roomId) throws InvalidRequestParameterException{
        return ResponseEntity.ok(seatService.findByRoomId(roomId));
    }

    @GetMapping("/getSeatHasCheckTicket")
    public ResponseEntity<?> getSeatHasCheckTicket(@RequestParam("id") Optional<Integer> id) throws InvalidRequestParameterException{
		
        return ResponseEntity.ok(seatService.getSeatHasCheckTicket(id));
    }
    
    
    
    @GetMapping("/getTotalPrice")
    public ResponseEntity<?> getTotalPrice(@RequestParam("showtimeid") Optional<Integer> showtimeid, 
    		@RequestParam("name") Optional<String> name) throws InvalidRequestParameterException{
        return ResponseEntity.ok(seatService.getTotal(showtimeid, name));
    }
}
