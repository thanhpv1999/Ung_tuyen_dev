package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.AboutDao;
import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.dao.PaymentDao;
import com.nhom20.cimeemappserver.dao.PaymentTypeDao;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.PaymentType;



@Service
public class PaymentTypeServiceImpl implements PaymentTypeService{

	@Autowired
	private PaymentTypeDao paymentTypeDao;

	@Override
	public List<PaymentType> listPaymentType() {
		// TODO Auto-generated method stub
		return paymentTypeDao.findAll();
	}

	

}
