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
import com.nhom20.cimeemappserver.entity.ProductPrice;
import com.nhom20.cimeemappserver.entity.Users;

import javax.transaction.Transactional;


public interface ProductPriceDao extends JpaRepository<ProductPrice, String>{

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO [dbo].[product_price]  ([product_id],[price_id],[active])\r\n"
			+ "VALUES (:percentage,:bookId,:active )",nativeQuery = true)
	void savePriceAndMovie(@Param(value = "percentage") String percentage,@Param(value = "bookId") String bookId,@Param(value = "active") boolean active);
	
}
