package com.nhom20.cimeemappserver.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.Order;
import com.nhom20.cimeemappserver.entity.OrderService;

import javax.transaction.Transactional;

public interface OrderDao extends JpaRepositoryImplementation<Order, String>{
	@Transactional
	@Query(value = "select * from [dbo].[orders]\r\n" + "where [salesman_id]=:userId", nativeQuery = true)
	public Page<Order> getDsOrder(Pageable pageable,@Param(value = "userId") String userId);
//	SELECT 
//	sum((order_detail_service.amount* order_detail_service.price)+price.value) as total
//	FROM     [order] INNER JOIN
//	                  orders_service ON [order].order_id = orders_service.order_id INNER JOIN
//	                  order_detail_service ON orders_service.orders_service_id = order_detail_service.orders_service_id INNER JOIN
//	                  booking ON [order].order_id = booking.order_id INNER JOIN
//	                  ticket_rate_price ON booking.ticket_rate_id = ticket_rate_price.ticket_rate_id INNER JOIN
//	                  price ON ticket_rate_price.price_id = price.price_id
//	where [order].order_id='CBCD4346-2BF0-4EFE-BF13-35324FA153C0'
	@Query(value = "SELECT \r\n"
			+ "sum((order_detail_service.amount* order_detail_service.price)+price.value) as total\r\n"
			+ "FROM     [orders] INNER JOIN\r\n"
			+ "                  orders_service ON [orders].order_id = orders_service.order_id INNER JOIN\r\n"
			+ "                  order_detail_service ON orders_service.orders_service_id = order_detail_service.orders_service_id INNER JOIN\r\n"
			+ "                  booking ON [orders].order_id = booking.order_id INNER JOIN\r\n"
			+ "                  ticket_rate_price ON booking.ticket_rate_id = ticket_rate_price.ticket_rate_id INNER JOIN\r\n"
			+ "                  price ON ticket_rate_price.price_id = price.price_id\r\n"
			+ "where [orders].order_id=:orderId",nativeQuery = true)
	public double getSumPriceByOrderId(@Param(value = "orderId") String orderId);
	
}
