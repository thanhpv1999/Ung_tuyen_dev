package com.nhom20.cimeemappserver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.TransactionDao;
import com.nhom20.cimeemappserver.entity.Transaction;

@Service
public class EPaymentDetailServiceImpl implements EPaymentDetailService{

	@Autowired
	private TransactionDao paymentDao;

	@Override
	public void addPayment(Transaction payment) {
		// TODO Auto-generated method stub
		paymentDao.save(payment);
	}

	


}
