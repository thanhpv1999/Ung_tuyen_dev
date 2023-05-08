package com.nhom20.cimeemappserver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.OrderDetailsDao;
import com.nhom20.cimeemappserver.entity.Order;
import com.nhom20.cimeemappserver.entity.OrderDetail;
import com.nhom20.cimeemappserver.entity.OrderService;


@Service
public class OrderDetailServiceImpl implements OrdersDetailService{

	@Autowired
	private OrderDetailsDao orderDetailsDao;

	@Override
	public void addOrdersDetail(OrderDetail orders) {
		// TODO Auto-generated method stub
		orderDetailsDao.save(orders);
	}

	@Override
	public List<OrderDetail> listOrder(String userId) {
		// TODO Auto-generated method stub
		return orderDetailsDao.listOrder(userId);
	}

	@Override
	public OrderDetail orderById(String ordersServiceId) {
		// TODO Auto-generated method stub
		Optional<OrderDetail> result=orderDetailsDao.findById(ordersServiceId);
		OrderDetail books=null;
		if (result.isPresent()) {
			books=result.get();
		}else {
			throw new RuntimeException("Did not find cart id - "+ordersServiceId);
		}
		return books;
	}

	



}
