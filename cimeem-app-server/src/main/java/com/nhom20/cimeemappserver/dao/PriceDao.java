package com.nhom20.cimeemappserver.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Address;
import com.nhom20.cimeemappserver.entity.Location;
import com.nhom20.cimeemappserver.entity.Price;
import com.nhom20.cimeemappserver.entity.Users;

import javax.transaction.Transactional;


public interface PriceDao extends JpaRepository<Price, String>{

	@Query(value = "SELECT top 1  price.value\r\n"
			+ "FROM     product INNER JOIN\r\n"
			+ "                  product_price ON product.product_id = product_price.product_id INNER JOIN\r\n"
			+ "                  price ON product_price.price_id = price.price_id\r\n"
			+ "where product.product_id=:id and product_price.active='true' \r\n"
			+ "and price.start_day < GETDATE() and price.end_day > GETDATE()"
			+ "",nativeQuery = true)
	public double priceByBookId(@Param(value = "id")String id);
	@Query(value = "SELECT top 1  price.import_price\r\n"
			+ "FROM     product INNER JOIN\r\n"
			+ "                  product_price ON product.product_id = product_price.product_id INNER JOIN\r\n"
			+ "                  price ON product_price.price_id = price.price_id\r\n"
			+ "where product.product_id=:id and product_price.active='true'  ORDER BY end_day DESC\r\n"
			+ "",nativeQuery = true)
	public double priceByBookIdEx(@Param(value = "id")String id);
	
	@Query(value = "SELECT  price.*\r\n"
			+ "FROM     product INNER JOIN\r\n"
			+ "                  product_price ON product.product_id = product_price.product_id INNER JOIN\r\n"
			+ "                  price ON product_price.price_id = price.price_id\r\n"
			+ "where product.product_id=:id and product_price.active='true'  ORDER BY end_day DESC\r\n"
			+ "",nativeQuery = true)
	public List<Price> listPriceByProductId(@Param(value = "id")String productId);
	//SELECT price.price_id, price.decs, price.value, price.currency_unit, price.active, price.title, price.start_day, price.end_day, price.import_price, price.retail_price,price.active
	//FROM     price INNER JOIN
//	                  ticket_rate_price ON price.price_id = ticket_rate_price.price_id INNER JOIN
//	                  ticket_rate ON ticket_rate_price.ticket_rate_id = ticket_rate.ticket_rate_id INNER JOIN
//	                  seat_type ON ticket_rate.seat_id = seat_type.seat_type_id
	//where seat_type.seat_type_id=1
	//GROUP BY seat_type.seat_type_id, price.price_id, price.decs, price.value, price.currency_unit, price.title, price.start_day, price.end_day, price.import_price, price.retail_price,price.active
	@Query(value = "SELECT price.price_id, price.decs, price.value, price.currency_unit, price.active, price.title, price.start_day, price.end_day, price.import_price, price.retail_price,price.active\r\n"
			+ "FROM     price INNER JOIN\r\n"
			+ "                  ticket_rate_price ON price.price_id = ticket_rate_price.price_id INNER JOIN\r\n"
			+ "                  ticket_rate ON ticket_rate_price.ticket_rate_id = ticket_rate.ticket_rate_id INNER JOIN\r\n"
			+ "                  seat_type ON ticket_rate.seat_id = seat_type.seat_type_id\r\n"
			+ "where seat_type.seat_type_id=:id\r\n"
			+ "GROUP BY seat_type.seat_type_id, price.price_id, price.decs, price.value, price.currency_unit, price.title, price.start_day, price.end_day, price.import_price, price.retail_price,price.active",nativeQuery = true)
	public List<Price> listPriceByType(@Param(value = "id")int productId);
}
