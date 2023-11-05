package com.example.demo.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.PaymentDetails;
import com.example.demo.exception.InvalidRequestParameterException;
import com.example.demo.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin("*")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @GetMapping({ "", "/" })
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody PaymentDetails data) throws InvalidRequestParameterException {
        return ResponseEntity.ok(paymentService.insertPaymentDetails(data));
    }

    @PostMapping("/update")
    public ResponseEntity<?> updatePayment(@RequestBody PaymentDetails data) throws InvalidRequestParameterException {
        return ResponseEntity.ok(paymentService.updateStatusPaymentDetails(data));
    }
}