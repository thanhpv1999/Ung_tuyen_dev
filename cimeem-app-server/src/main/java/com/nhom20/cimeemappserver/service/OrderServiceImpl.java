package com.nhom20.cimeemappserver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.OrderDao;
import com.nhom20.cimeemappserver.dao.OrderDetailsDao;
import com.nhom20.cimeemappserver.dao.OrderServiceDao;
import com.nhom20.cimeemappserver.entity.Order;
import com.nhom20.cimeemappserver.entity.OrderDetail;
import com.nhom20.cimeemappserver.entity.OrderService;


@Service
public class OrderServiceImpl implements OrdersService{

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderDetailsDao orderDetailsDao;

	@Override
	public void addOrders(Order orders) {
		// TODO Auto-generated method stub
		orderDao.save(orders);
	}

	@Override
	public Order findOrderId(String orderId) {
		// TODO Auto-generated method stub
		Optional<Order> result=orderDao.findById(orderId);
		Order books=null;
		if (result.isPresent()) {
			books=result.get();
		}else {
			throw new RuntimeException("Did not find cart id - "+orderId);
		}
		return books;
	}

	@Override
	public Page<Order> getDsOrder(Pageable pageable, String userId) {
		// TODO Auto-generated method stub
		return orderDao.getDsOrder(pageable, userId);
	}

	@Override
	public double getSumPriceByOrderid(String orderId) {
		// TODO Auto-generated method stub
		try {
			return orderDao.getSumPriceByOrderId(orderId);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	public List<OrderDetail> orderById(String orderId) {
		// TODO Auto-generated method stub
		return orderDetailsDao.orderById(orderId);
	}
	


}
