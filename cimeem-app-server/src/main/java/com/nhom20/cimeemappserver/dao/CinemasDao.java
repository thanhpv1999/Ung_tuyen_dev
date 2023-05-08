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
import com.nhom20.cimeemappserver.entity.Cinemas;
import com.nhom20.cimeemappserver.entity.Location;
import com.nhom20.cimeemappserver.entity.Users;

import javax.transaction.Transactional;


public interface CinemasDao extends JpaRepository<Cinemas, String>{
//	select * from [dbo].[cinemas] where [theatre_id]='45fee4de-160c-41fa-a2cd-869055110c20'
	@Query(value = "select * from [dbo].[cinemas] where [theatre_id]=:id",nativeQuery = true)
	public List<Cinemas> listCinemaByThearte(@Param(value = "id")String id);
}
