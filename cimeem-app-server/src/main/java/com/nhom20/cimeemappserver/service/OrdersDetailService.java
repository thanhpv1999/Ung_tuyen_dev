package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.OrderDetail;
import com.nhom20.cimeemappserver.entity.OrderService;



public interface OrdersDetailService {

	public void addOrdersDetail(OrderDetail orders);
	public List<OrderDetail> listOrder(String userId);
	public OrderDetail orderById(String ordersServiceId);
	
}
