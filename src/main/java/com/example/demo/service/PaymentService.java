package com.example.demo.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.admin.controller.enums.RequestParameterEnum;
import com.example.demo.dao.PaymentDetailsDao;
import com.example.demo.entity.PaymentDetails;
import com.example.demo.exception.InvalidRequestParameterException;

@Service
public class PaymentService {
	@Autowired
	private PaymentDetailsDao paymentDetailsDao;

	public void insertPaymentDetails(PaymentDetails paymentDetails) throws InvalidRequestParameterException {
		paymentDetailsDao.insert(paymentDetails);
	}

	public void updateStatusPaymentDetails(PaymentDetails paymentDetails) throws InvalidRequestParameterException {
		paymentDetailsDao.updateStatus(paymentDetails);
	}

	public PaymentDetails findByTransactionNo(Optional<String> vnp_TransactionNo)
			throws InvalidRequestParameterException {
		vnp_TransactionNo.orElseThrow(
				() -> new InvalidRequestParameterException("vnp_TransactionNo", RequestParameterEnum.INVALID_TYPE));
		return paymentDetailsDao.findByTransactionNo(vnp_TransactionNo.get());
	}
}
