package com.nhom20.cimeemappserver.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.nhom20.cimeemappserver.entity.Booking;

public interface BookingDao extends JpaRepository<Booking, String>{
	@Transactional(readOnly=true)
	@Query(value = "SELECT type_ticket.type,count( booking.order_id) as count,sum(  price.value) as price,MIN( price.value) as min_price,MAX( price.value) as max_price,AVG( price.value) as avg_price\r\n"
			+ "FROM     booking INNER JOIN\r\n"
			+ "                  ticket_rate ON booking.ticket_rate_id = ticket_rate.ticket_rate_id INNER JOIN\r\n"
			+ "                  seat_type ON ticket_rate.seat_id = seat_type.seat_type_id INNER JOIN\r\n"
			+ "                  type_ticket ON ticket_rate.type_ticket_id = type_ticket.type_ticket_id INNER JOIN\r\n"
			+ "                  ticket_rate_price ON ticket_rate.ticket_rate_id = ticket_rate_price.ticket_rate_id INNER JOIN\r\n"
			+ "                  price ON ticket_rate_price.price_id = price.price_id  INNER JOIN\r\n"
			+ "                  [dbo].[orders] ON booking.order_id = [dbo].[orders].order_id\r\n"
			+ " where [dbo].[orders].salesman_id=:userId\r\n"
			+ "GROUP BY type_ticket.type",nativeQuery = true)
	public List<Object[]> inventoryByCategory(@Param(value = "userId") String userId);
	@Transactional(readOnly=true)
	@Query(value = "SELECT product_type.type, sum(product.amount) as amount,sum(product.amount* price.value) as sum_price,MIN(price.value) as min_price,MAX(price.value) as max_price,AVG(price.value) as avg_price\r\n"
			+ "FROM     product INNER JOIN\r\n"
			+ "                  product_type ON product.product_type = product_type.product_type_id INNER JOIN\r\n"
			+ "                  order_detail_service ON product.product_id = order_detail_service.product_id INNER JOIN\r\n"
			+ "                  orders_service ON order_detail_service.orders_service_id = orders_service.orders_service_id INNER JOIN\r\n"
			+ "                  [orders] ON orders_service.order_id = [orders].order_id INNER JOIN\r\n"
			+ "                  [dbo].[product_price] ON product.product_id = product_price.product_id\r\n"
			+ "				   INNER JOIN\r\n"
			+ "                  price ON product_price.price_id = price.price_id\r\n"
			+ "				  where [dbo].[orders].salesman_id=:userId\r\n"
			+ "GROUP BY product_type.type",nativeQuery = true)
	public List<Object[]> revenueByCategory(@Param(value = "userId") String userId);
	@Query(value = "select YEAR(order_detail_service.created_at) as year,SUM(order_detail_service.amount) as sum_amount,\r\n"
			+ "					SUM(order_detail_service.amount*order_detail_service.price) as sum,MIN(order_detail_service.price) as min_price,MAX(order_detail_service.price) as max_price,\r\n"
			+ "			AVG(order_detail_service.price) as avg_price \r\n"
			+ "				FROM     product INNER JOIN\r\n"
			+ "                  order_detail_service ON product.product_id = order_detail_service.product_id INNER JOIN\r\n"
			+ "                  orders_service ON order_detail_service.orders_service_id = orders_service.orders_service_id INNER JOIN\r\n"
			+ "                  [orders] ON orders_service.order_id = [orders].order_id\r\n"
			+ "				 where [dbo].[orders].salesman_id=:userId\r\n"
			+ "			GROUP BY YEAR(order_detail_service.created_at) ORDER by YEAR(order_detail_service.created_at) DESC",nativeQuery = true)
	public List<Object[]> revenueByYear(@Param(value = "userId")String userId);

	@Query(value = "select MONTH(order_detail_service.created_at) as month,SUM(order_detail_service.amount) as sum_amount,\r\n"
			+ "					SUM(order_detail_service.amount*order_detail_service.price) as sum,MIN(order_detail_service.price) as min_price,MAX(order_detail_service.price) as max_price,\r\n"
			+ "			AVG(order_detail_service.price) as avg_price \r\n"
			+ "				FROM     product INNER JOIN\r\n"
			+ "                  order_detail_service ON product.product_id = order_detail_service.product_id INNER JOIN\r\n"
			+ "                  orders_service ON order_detail_service.orders_service_id = orders_service.orders_service_id INNER JOIN\r\n"
			+ "                  [orders] ON orders_service.order_id = [orders].order_id\r\n"
			+ "				 where [dbo].[order].salesman_id=:userId\r\n"
			+ "			GROUP BY MONTH(order_detail_service.created_at) ORDER by MONTH(order_detail_service.created_at) DESC",nativeQuery = true)
	public List<Object[]> revenueByMONTH(@Param(value = "userId")String userId);
	@Query(value = "select CEILING(MONTH(order_detail_service.created_at)/3.0) as month,SUM(order_detail_service.amount) as sum_amount,\r\n"
			+ "					SUM(order_detail_service.amount*order_detail_service.price) as sum,MIN(order_detail_service.price) as min_price,MAX(order_detail_service.price) as max_price,\r\n"
			+ "			AVG(order_detail_service.price) as avg_price \r\n"
			+ "				FROM     product INNER JOIN\r\n"
			+ "                  order_detail_service ON product.product_id = order_detail_service.product_id INNER JOIN\r\n"
			+ "                  orders_service ON order_detail_service.orders_service_id = orders_service.orders_service_id INNER JOIN\r\n"
			+ "                  [orders] ON orders_service.order_id = [orders].order_id\r\n"
			+ "				 where [dbo].[orders].salesman_id=:userId\r\n"
			+ "			GROUP BY CEILING(MONTH(order_detail_service.created_at)/3.0) ORDER by CEILING(MONTH(order_detail_service.created_at)/3.0) DESC",nativeQuery = true)
	public List<Object[]> revenueByQuater(@Param(value = "userId")String userId);
	@Query(value = "\r\n"
			+ "select sum(amount) from [dbo].[product] ",nativeQuery = true)
	public int countQuantityProduct();
	@Query(value = "	select count(RowNum) from(\r\n"
			+ "		\r\n"
			+ "			 SELECT ROW_NUMBER() OVER (ORDER BY [dbo].[Orders].[order_id]) AS RowNum\r\n"
			+ "				FROM   product INNER JOIN\r\n"
			+ "                  order_detail_service ON product.product_id = order_detail_service.product_id \r\n"
			+ "				  INNER JOIN\r\n"
			+ "                  orders_service ON order_detail_service.orders_service_id = orders_service.orders_service_id\r\n"
			+ "				  INNER JOIN\r\n"
			+ "                  [dbo].[orders] ON orders_service.order_id = [dbo].[orders].order_id\r\n"
			+ "					 where [dbo].[orders].salesman_id=:userId\r\n"
			+ "							group by [dbo].[Orders].order_id\r\n"
			+ "				) as RowNum",nativeQuery = true)
	public int countOrder(@Param(value = "userId")String userId) ;
	@Query(value = "	select count(RowNum) from(\r\n"
			+ "		\r\n"
			+ "			 SELECT sum(order_detail_service.[amount]*order_detail_service.[price]) AS RowNum\r\n"
			+ "				FROM   product INNER JOIN\r\n"
			+ "                  order_detail_service ON product.product_id = order_detail_service.product_id \r\n"
			+ "				  INNER JOIN\r\n"
			+ "                  orders_service ON order_detail_service.orders_service_id = orders_service.orders_service_id\r\n"
			+ "				  INNER JOIN\r\n"
			+ "                  [dbo].[orders] ON orders_service.order_id = [dbo].[orders].order_id\r\n"
			+ "					 where [dbo].[orders].salesman_id=:userId\r\n"
			+ "							group by [dbo].[Orders].order_id\r\n"
			+ "				) as RowNum",nativeQuery = true)
	public int salesOrderDetail(@Param(value = "userId")String userId) ;


}
