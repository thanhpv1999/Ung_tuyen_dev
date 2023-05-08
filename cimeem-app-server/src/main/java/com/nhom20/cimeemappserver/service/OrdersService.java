package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.Order;
import com.nhom20.cimeemappserver.entity.OrderDetail;
import com.nhom20.cimeemappserver.entity.OrderService;


public interface OrdersService {

	public void addOrders(Order orders);

	public Order findOrderId(String orderId);
	public Page<Order> getDsOrder(Pageable pageable, String userId);

	public double getSumPriceByOrderid(String orderId);
	public List<OrderDetail> orderById(String orderId);
}
