package com.nhom20.cimeemappserver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.PaymentDao;
import com.nhom20.cimeemappserver.entity.Payment;


@Service
public class EPaymentServiceImpl implements EPaymentService{

	@Autowired
	private PaymentDao paymentDao;

	@Override
	public void addPayment(Payment payment) {
		// TODO Auto-generated method stub
		paymentDao.save(payment);
	}

	@Override
	public Payment findPaymentId(String paymentId) {
		// TODO Auto-generated method stub
		Optional<Payment> result=paymentDao.findById(paymentId);
		Payment books=null;
		if (result.isPresent()) {
			books=result.get();
		}else {
			throw new RuntimeException("Did not find payment id - "+paymentId);
		}
		return books;
	}

	


}
