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
import com.nhom20.cimeemappserver.entity.Product;
import com.nhom20.cimeemappserver.entity.Promotion;
import com.nhom20.cimeemappserver.entity.Users;

import javax.transaction.Transactional;


public interface ProductDao extends JpaRepository<Product, String>{

	@Query(value = "SELECT product.product_id, product.name, product.amount, product.product_type, product.description,product.img,product.id\r\n"
			+ "FROM     price INNER JOIN\r\n"
			+ "                  product_price ON price.price_id = product_price.price_id INNER JOIN\r\n"
			+ "                  product ON product_price.product_id = product.product_id\r\n"
			+ "where price.price_id=:headerId\r\n"
			+ "GROUP BY product.product_id, product.name, product.amount, product.product_type, product.description, product.img,product.id",nativeQuery = true)
	public List<Product> listProductByHeader(@Param("headerId") String headerId);
	@Modifying
	@Transactional
	@Query(value = "UPDATE [dbo].[product]\r\n"
			+ "		SET [name]=:gen, [amount]=:gen2,[description]=:gen3,[img]=:gen4 WHERE [product_id]=:id",nativeQuery = true)
	public void updateProduct(@Param(value = "gen") String gen,@Param(value = "gen2") String gen2,@Param(value = "gen3") String gen3,@Param(value = "gen4") String gen4,@Param(value = "id") String id);
	@Query(value = "select * from product where product.id=:id",nativeQuery = true)
	public List<Product> listById(@Param(value = "id")String string);
	
}
