package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PaymentDetailsDao;
import com.example.demo.dto.PaymentDetailsDto;
import com.example.demo.entity.PaymentDetails;
import com.example.demo.exception.InvalidRequestParameterException;

@Service
public class PaymentService {
    @Autowired
    private PaymentDetailsDao paymentDetailsDao;

    public List<PaymentDetailsDto> findAll() {
        return paymentDetailsDao.findAll();
    }

    public int insertPaymentDetails(PaymentDetails paymentDetails) throws InvalidRequestParameterException {
        return paymentDetailsDao.insert(paymentDetails);
    }

    public int updateStatusPaymentDetails(PaymentDetails paymentDetails) throws InvalidRequestParameterException {
        return paymentDetailsDao.updateStatus(paymentDetails);
    }

    public PaymentDetails findByTransactionNo(Optional<String> vnp_TransactionNo) throws InvalidRequestParameterException {
        vnp_TransactionNo.orElseThrow();

        return paymentDetailsDao.findByTransactionNo(vnp_TransactionNo.get());
    }
}
