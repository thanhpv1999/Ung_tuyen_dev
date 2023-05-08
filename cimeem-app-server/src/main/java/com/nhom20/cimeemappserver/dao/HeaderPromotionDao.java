package com.nhom20.cimeemappserver.dao;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.entity.HeaderPromotion;

import lombok.val;


public interface HeaderPromotionDao extends JpaRepositoryImplementation<HeaderPromotion, String>{
	@Modifying
	@Transactional
	@Query(value = "UPDATE [dbo].[header_promotion]\r\n"
			+ "		SET [title]=:gen, [details]=:gen2,[active]=:gen3 WHERE [id]=:id",nativeQuery = true)
	public void updateHeaderPromotion(@Param(value = "gen") String gen,@Param(value = "gen2") String gen2,@Param(value = "gen3") String gen3,@Param(value = "id") String id);
	

}
