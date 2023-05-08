package com.nhom20.cimeemappserver.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.Promotion;



public interface PromotionDao extends JpaRepositoryImplementation<Promotion, String>{
	
//	SELECT promotion.id, promotion.status, promotion.expiration_date, promotion.code,promotion.discount,promotion.details,promotion.type,promotion.discount_amount
//	FROM     coupon_redemption INNER JOIN
//	                  promotion ON coupon_redemption.code = promotion.id
//					  where book_id='d938d5a4-2b52-40c3-a748-215515b4bbdd' and status= 'true' and expiration_date>= CAST( GETDATE() AS Date )
//	GROUP BY promotion.id, promotion.status, promotion.expiration_date, promotion.code,promotion.discount,promotion.details,promotion.type,promotion.discount_amount
	@Query(value = "SELECT promotion.id, promotion.status, promotion.expiration_date, promotion.code,promotion.discount,promotion.details,promotion.type,promotion.discount_amount\r\n"
			+ "FROM     coupon_redemption INNER JOIN\r\n"
			+ "                  promotion ON coupon_redemption.code = promotion.id\r\n"
			+ "				  where book_id=:bookId and status= 'true' and expiration_date>= CAST( GETDATE() AS Date )\r\n"
			+ "GROUP BY promotion.id, promotion.status, promotion.expiration_date, promotion.code,promotion.discount,promotion.details,promotion.type,promotion.discount_amount ORDER BY promotion.discount desc\r\n"
			+ "",nativeQuery = true)
	public List<Promotion> listPromotionByBook(@Param("bookId") String bookId);
//	SELECT promotion.id, promotion.code, promotion.discount_amount, promotion.expiration_date, 
//	promotion.type, promotion.details, promotion.discount, promotion.status
//	FROM     promotion INNER JOIN
//	                  advertising_program ON promotion.id = advertising_program.code
//	where header_id='36576024-8e6c-425c-b042-749a7cf8955f'	
//	GROUP BY promotion.id, promotion.code, promotion.discount_amount, promotion.expiration_date, promotion.type, 
//	promotion.details, promotion.discount, promotion.status
	@Query(value = "SELECT promotion.id, promotion.code, promotion.discount_amount, promotion.expiration_date, \r\n"
			+ "promotion.type, promotion.details, promotion.discount, promotion.status\r\n"
			+ "FROM     promotion INNER JOIN\r\n"
			+ "                  advertising_program ON promotion.id = advertising_program.code\r\n"
			+ "where header_id=:headerId	\r\n"
			+ "GROUP BY promotion.id, promotion.code, promotion.discount_amount, promotion.expiration_date, promotion.type, \r\n"
			+ "promotion.details, promotion.discount, promotion.status",nativeQuery = true)
	public List<Promotion> listPromotionByHeader(@Param("headerId") String headerId);
//	SELECT promotion.id, promotion.code, promotion.discount_amount, promotion.expiration_date, 
//	promotion.type, promotion.details, promotion.discount, promotion.status
//	FROM     promotion INNER JOIN
//	                  advertising_program ON promotion.id = advertising_program.code
//	INNER JOIN
//	                  header_promotion ON header_promotion.id = advertising_program.header_id
//	where header_promotion.created_at < GETDATE() and header_promotion.expiration_date > GETDATE()
//	GROUP BY promotion.id, promotion.code, promotion.discount_amount, promotion.expiration_date, promotion.type, 
//	promotion.details, promotion.discount, promotion.status
	@Query(value = "SELECT promotion.id, promotion.code, promotion.discount_amount, promotion.expiration_date, \r\n"
			+ "	promotion.type, promotion.details, promotion.discount, promotion.status\r\n"
			+ "	FROM     promotion INNER JOIN\r\n"
			+ "	                  advertising_program ON promotion.id = advertising_program.code\r\n"
			+ "	INNER JOIN\r\n"
			+ "	                  header_promotion ON header_promotion.id = advertising_program.header_id\r\n"
			+ "	where header_promotion.created_at < GETDATE() and header_promotion.expiration_date > GETDATE()\r\n"
			+ "	GROUP BY promotion.id, promotion.code, promotion.discount_amount, promotion.expiration_date, promotion.type, \r\n"
			+ "	promotion.details, promotion.discount, promotion.status\r\n"
			+ "",nativeQuery = true)
	public List<Promotion> listPromotionByHeaderEpx();
	
	
}
