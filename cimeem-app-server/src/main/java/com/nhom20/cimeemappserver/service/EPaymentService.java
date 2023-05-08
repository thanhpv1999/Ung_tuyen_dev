package com.nhom20.cimeemappserver.service;

import java.util.List;

import com.nhom20.cimeemappserver.entity.Payment;



public interface EPaymentService {

	public void addPayment(Payment payment);
	public Payment findPaymentId(String paymentId);
}
