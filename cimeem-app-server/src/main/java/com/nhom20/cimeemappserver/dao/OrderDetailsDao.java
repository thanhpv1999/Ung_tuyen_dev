package com.nhom20.cimeemappserver.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.OrderDetail;
import com.nhom20.cimeemappserver.entity.OrderService;

import javax.transaction.Transactional;

public interface OrderDetailsDao extends JpaRepositoryImplementation<OrderDetail, String>{
	
//	SELECT o.order_id, od.book_id, od.price,o.status_id, sum( od.amount* od.price), od.book_id
//	FROM     order_detail od INNER JOIN
//	                  status s ON od.status_id = s.status_id INNER JOIN
//	                  orders o ON od.order_id = o.order_id AND s.status_id = o.status_id INNER JOIN
//	                  users u ON o.[user_id] = u.[user_id]
//			  where u.[user_id]='1448076d-711e-41ef-aa6a-e6bad2683723' and s.name='WAITING'
//	GROUP BY o.order_id, od.book_id, od.price,o.status_id,  od.book_id
	@Query(value = "SELECT od.order_id, od.book_id, od.price,o.status_id, sum( od.amount* od.price) as tong,od.amount,od.amount_ship,od.created_at,od.discount,od.updated_at\r\n"
			+ "FROM     order_detail od INNER JOIN\r\n"
			+ "                  status s ON od.status_id = s.status_id INNER JOIN\r\n"
			+ "                  orders o ON od.order_id = o.order_id AND s.status_id = o.status_id INNER JOIN\r\n"
			+ "                  users u ON o.[user_id] = u.[user_id]\r\n"
			+ "		  where u.[user_id]=:userId and s.name='WAITING'\r\n"
			+ "GROUP BY od.order_id, od.book_id, od.price,o.status_id,od.amount,od.amount_ship,od.created_at,od.discount,od.updated_at",nativeQuery = true)
	public List<OrderDetail> listOrder(@Param(value = "userId") String userId);
	@Query(value = "\r\n"
			+ "SELECT order_detail_service.orders_service_id, order_detail_service.product_id, order_detail_service.amount, order_detail_service.status_id, order_detail_service.created_at, order_detail_service.updated_at, order_detail_service.discount, \r\n"
			+ "                  order_detail_service.price, order_detail_service.theatre_id, order_detail_service.cinema_id\r\n"
			+ "FROM     [orders] INNER JOIN\r\n"
			+ "                  orders_service ON [orders].order_id = orders_service.order_id INNER JOIN\r\n"
			+ "                  order_detail_service ON orders_service.orders_service_id = order_detail_service.orders_service_id\r\n"
			+ "where [orders].order_id=:id\r\n"
			+ "GROUP BY order_detail_service.orders_service_id, order_detail_service.product_id, order_detail_service.amount, order_detail_service.status_id, order_detail_service.created_at, order_detail_service.updated_at, order_detail_service.discount, \r\n"
			+ "                  order_detail_service.price, order_detail_service.theatre_id, order_detail_service.cinema_id\r\n"
			+ "",nativeQuery = true)
	public List<OrderDetail> orderById(@Param(value = "id") String orderId);
	
}
